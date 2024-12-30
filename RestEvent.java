class RestEvent extends Event {
    private final Operator operator;

    RestEvent(Customer customer, Operator operator, double eventTime) {
        super(customer, eventTime);
        this.operator = operator;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        double restTime = 0.0;
        Operator updatedOperator = this.operator;
        if (this.operator.isHuman()) {
            restTime = shop.getRestTime();
            updatedOperator = shop.getUpdatedOperator(this.operator).rest(restTime);
        }
        return new Pair<Event, Shop>(this, shop.update(updatedOperator));
    }

    boolean hasNextEvent() {
        return false;
    }

    boolean hasNextShop() {
        return true;
    }

    Pair<String, Double> stat() {
        return new Pair<String, Double>("", 0.0);
    }

    @Override
    public String toString() {
        return "";
    }
}
