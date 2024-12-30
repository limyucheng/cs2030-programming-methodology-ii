class DoneEvent extends Event {
    private final Operator operator;

    DoneEvent(Customer customer, Operator operator, double eventTime) {
        super(customer, eventTime);
        this.operator = operator;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        return new Pair<Event, Shop>(new RestEvent(super.customer, this.operator,
            super.eventTime), shop);
    }

    boolean hasNextEvent() {
        return true;
    }

    boolean hasNextShop() {
        return false;
    }

    Pair<String, Double> stat() {
        return new Pair<String, Double>("", 0.0);
    }

    @Override
    public String toString() {
        return super.toString() + " done";
    }
}
