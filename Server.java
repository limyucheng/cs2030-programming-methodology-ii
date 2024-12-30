class Server extends Operator {

    Server(int id) {
        super(id);
    }

    private Server(int id, double earliestAvail, int numInQueue) {
        super(id, earliestAvail, numInQueue);
    }

    Operator serve(Customer customer, double serviceTime) {
        return new Server(super.id, customer.serveTill(serviceTime),
            Math.max(super.numInQueue - 1, 0));
    }

    Operator queue() {
        return new Server(super.id, super.earliestAvail, super.numInQueue + 1);
    }

    Operator rest(double restTime) {
        return new Server(super.id, super.earliestAvail + restTime, super.numInQueue);
    }

    boolean isHuman() {
        return true;
    }

    boolean canServe(Customer customer) {
        return customer.canBeServed(super.earliestAvail);
    }

    boolean canWait(int qmax, int numInSelfCheckQ) {
        return super.numInQueue < qmax;
    }

    boolean sameId(Operator other) {
        return super.id == other.id;
    }

    double endServe() {
        return super.earliestAvail;
    }

    @Override
    public String toString() {
        return "server " + super.id;
    }
}
