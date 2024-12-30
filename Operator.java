abstract class Operator {
    protected final int id;
    protected final double earliestAvail;
    protected final int numInQueue;

    Operator(int id) {
        this.id = id;
        this.earliestAvail = 0.0;
        this.numInQueue = 0;
    }

    Operator(int id, double earliestAvail, int numInQueue) {
        this.id = id;
        this.earliestAvail = earliestAvail;
        this.numInQueue = numInQueue;
    }

    abstract Operator serve(Customer customer, double svcTime);

    abstract Operator queue();

    abstract Operator rest(double restTime);

    abstract boolean isHuman();

    abstract boolean canServe(Customer customer);

    abstract boolean canWait(int qmax, int selfCheckQSize);

    abstract boolean sameId(Operator other);

    abstract double endServe();

    public double getEarliestAvail() {
        return this.earliestAvail;
    }
    
    @Override
    public String toString() {
        return "operator " + this.id;
    }
}
