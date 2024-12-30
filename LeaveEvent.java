class LeaveEvent extends Event {

    LeaveEvent(Customer customer, double eventTime) {
        super(customer, eventTime);
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        return new Pair<Event, Shop>(this, shop);
    }

    boolean hasNextEvent() {
        return false;
    }

    boolean hasNextShop() {
        return false;
    }

    Pair<String, Double> stat() {
        return new Pair<String, Double>("l", 0.0);
    }

    @Override
    public String toString() {
        return super.toString() + " leaves";
    }
}
