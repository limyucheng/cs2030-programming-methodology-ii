import java.util.Optional;
import java.util.stream.Stream;
import java.lang.String;
import java.lang.Math;

class State {
    private final Shop shop;
    private final PQ<Event> pq;
    private final PQ<Event> oldPQ;
    private final String output;
    private final Statistics stats;

    State(PQ<Event> pq, Shop shop) {
        this.pq = pq;
        this.oldPQ = pq;
        this.shop = shop;
        this.output = "";
        this.stats = new Statistics(0, 0, 0);
    }

    private State(PQ<Event> pq, PQ<Event> oldPQ, Shop shop, String output, Statistics stats) {
        this.pq = pq;
        this.oldPQ = oldPQ;
        this.shop = shop;
        this.output = output;
        this.stats = stats;
    }

    State next() {
        return this.pq.poll().t()
            .map(e -> {
                PQ<Event> newPQ = this.pq.poll().u();
                Shop newShop = this.shop;
                String newOutput = (this.output + e.toString()).trim() + "\n";
                Statistics newStats = this.stats.update(e.stat());
                if (e.hasNextEvent() || e.hasNextShop()) {
                    Pair<Event, Shop> nextES = e.next(this.shop);
                    if (e.hasNextEvent()) {
                        newPQ = newPQ.add(nextES.t());
                    }
                    if (e.hasNextShop()) {
                        newShop = nextES.u();
                    }
                }
                return new State(newPQ, this.pq, newShop, newOutput, newStats);
            })
            .orElse(new State(this.pq, this.shop));
    }

    boolean isEmpty() {
        return this.oldPQ.isEmpty();
    }

    Statistics getStats() {
        return this.stats;
    }

    public String toString() {
        return this.output.trim();
    }
}
