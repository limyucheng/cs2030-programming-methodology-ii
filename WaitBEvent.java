class WaitBEvent extends Event {
    private final Operator operator;

    WaitBEvent(Customer customer, Operator operator, double eventTime) {
        super(customer, eventTime);
        this.operator = operator;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        Operator updatedOperator = shop.getUpdatedOperator(this.operator);
        if (updatedOperator.canServe(super.customer)) {
            return new Pair<Event, Shop>(new ServeEvent(super.customer, updatedOperator,
                super.eventTime), shop);
        }
        if (updatedOperator.isHuman()) {
            Customer updatedCustomer = super.customer.wait(updatedOperator.endServe());
            return new Pair<Event, Shop>(new WaitBEvent(updatedCustomer, updatedOperator,
                updatedOperator.endServe()), shop);
        }
        updatedOperator = shop.getEarliestSelfCheck(updatedOperator);
        Customer updatedCustomer = super.customer.wait(updatedOperator.endServe());
        return new Pair<Event, Shop>(new WaitBEvent(updatedCustomer, updatedOperator,
            updatedOperator.endServe()), shop);
    }

    boolean hasNextEvent() {
        return true;
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
