class WaitAEvent extends Event {
    private final Operator operator;

    WaitAEvent(Customer customer, Operator operator, double eventTime) {
        super(customer, eventTime);
        this.operator = operator;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        if (this.operator.isHuman()) {
            double waitTill = this.operator.endServe();
            Operator updatedOperator = this.operator.queue();
            Shop updatedShop = shop.update(updatedOperator);
            Customer updatedCustomer = super.customer.wait(waitTill);
            return new Pair<Event, Shop>(new WaitBEvent(updatedCustomer, updatedOperator, waitTill),
                updatedShop);
        }
        Shop updatedShop = shop.queue();
        Operator updatedOperator = shop.getEarliestSelfCheck(this.operator);
        double waitTill = updatedOperator.endServe();
        Customer updatedCustomer = super.customer.wait(waitTill);
        return new Pair<Event, Shop>(new WaitBEvent(updatedCustomer, updatedOperator, waitTill),
            updatedShop);
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
        return super.toString() + " waits at " + this.operator;
    }
}
