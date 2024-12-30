class SelfCheck extends Operator {

    SelfCheck(int id) {
        super(id);
    }

    private SelfCheck(int id, double earliestAvail, int numInQueue) {
        super(id, earliestAvail, numInQueue);
    }

    Operator serve(Customer customer, double serviceTime) {
        return new SelfCheck(super.id, customer.serveTill(serviceTime),
            Math.max(super.numInQueue - 1, 0));
    }

    Operator queue() {
        return new SelfCheck(super.id, super.earliestAvail, super.numInQueue + 1);
    }

    Operator rest(double restTime) {
        return this;
    }

    boolean isHuman() {
        return false;
    }

    boolean canServe(Customer customer) {
        return customer.canBeServed(super.earliestAvail);
    }

    boolean canWait(int qmax, int numInSelfCheckQ) {
        return numInSelfCheckQ < qmax;
    }

    boolean sameId(Operator other) {
        return super.id == other.id;
    }

    double endServe() {
        return super.earliestAvail;
    }

    @Override
    public String toString() {
        return "self-check " + super.id;
    }
}
