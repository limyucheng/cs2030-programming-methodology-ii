abstract class Event implements Comparable<Event> { 
    protected final Customer customer;
    protected final double eventTime;

    Event(Customer customer, double eventTime) {
        this.customer = customer;
        this.eventTime = eventTime;
    }

    abstract Pair<Event, Shop> next(Shop shop);

    abstract boolean hasNextEvent();

    abstract boolean hasNextShop();

    abstract Pair<String, Double> stat();

    @Override
    public int compareTo(Event other) {
        if (this.eventTime < other.eventTime) {
            return -1;
        } else if (this.eventTime == other.eventTime) {
            return this.customer.compareTo(other.customer);
        }
        return 1;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.eventTime) + " " + this.customer;
    }
}
