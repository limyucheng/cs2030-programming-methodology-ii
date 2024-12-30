import java.util.Optional;

class ArriveEvent extends Event {

    ArriveEvent(Customer customer, double eventTime) {
        super(customer, eventTime);
    }

    @Override
    Pair<Event, Shop> next(Shop shop) {
        Optional<Operator> operator = shop.findOperator(super.customer);
        return operator
        .map(op -> new Pair<Event, Shop>(
            new ServeEvent(super.customer, op, super.eventTime), shop))
        .orElse(shop.findQueue()
            .map(op -> new Pair<Event, Shop>(
                new WaitAEvent(super.customer, op, super.eventTime), shop))
            .orElse(new Pair<Event, Shop>(
                new LeaveEvent(super.customer, super.eventTime), shop)));
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
        return super.toString() +  " arrives";
    }
}
