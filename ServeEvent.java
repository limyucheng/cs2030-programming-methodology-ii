class ServeEvent extends Event {
    private final Operator operator;

    ServeEvent(Customer customer, Operator operator, double eventTime) {
        super(customer, eventTime);
        this.operator = operator;
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        double serviceTime = shop.getServiceTime();
        Operator updatedOperator = this.operator.serve(super.customer, serviceTime);
        Shop updatedShop = shop.update(updatedOperator);
        if (!updatedOperator.isHuman()) {
            updatedShop = updatedShop.unqueue();
        }
        return new Pair<Event, Shop>(new DoneEvent(super.customer, updatedOperator,
            updatedOperator.endServe()), updatedShop);
    }

    boolean hasNextEvent() {
        return true;
    }

    boolean hasNextShop() {
        return true;
    }

    Pair<String, Double> stat() {
        return new Pair<String, Double>("s", super.customer.waitTime());
    }

    @Override
    public String toString() {
        return super.toString() + " serves by " + this.operator;
    }
}
