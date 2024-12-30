import java.util.List;
import java.util.stream.Stream;
import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int numOfSelfChecks;
    private final int qmax;
    private final Supplier<Double> serviceTimes;
    private final Supplier<Double> restTimes;
    private final int numOfCustomers;
    private final List<Pair<Integer, Double>> arrivals;

    Simulator(int numOfServers, int numOfSelfChecks, int qmax, Supplier<Double> serviceTimes,
        Supplier<Double> restTimes, int numOfCustomers, List<Pair<Integer, Double>> arrivals) {
        this.numOfServers = numOfServers;
        this.numOfSelfChecks = numOfSelfChecks;
        this.qmax = qmax;
        this.serviceTimes = serviceTimes;
        this.restTimes = restTimes;
        this.numOfCustomers = numOfCustomers;
        this.arrivals = arrivals;
    }

    String run() {
        Shop shop = new Shop(this.numOfServers, this.numOfSelfChecks,
            this.serviceTimes, this.restTimes, this.qmax);
        PQ<Event> pq = new PQ<Event>(arrivals.stream()
            .map(a -> new ArriveEvent(new Customer(a.t(), a.u()), a.u()))
            .toList());
        State initState = new State(pq, shop);
        State finalState = Stream.iterate(new State(pq, shop),
            state -> !state.isEmpty(),
            state -> state.next())
            .reduce(initState, (x, y) -> y);
        return finalState + "\n" + finalState.getStats();
    }
}
