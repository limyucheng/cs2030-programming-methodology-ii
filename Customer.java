class Customer implements Comparable<Customer> {
    private final int id;
    private final double arrTime;
    private final double endWait;

    Customer(int id, double arrTime) {
        this.id = id;
        this.arrTime = arrTime;
        this.endWait = arrTime;
    }

    Customer(int id, double arrTime, double endWait) {
        this.id = id;
        this.arrTime = arrTime;
        this.endWait = endWait;
    }

    boolean canBeServed(double time) {
        return this.endWait >= time;
    }

    double serveTill(double serviceTime) {
        return this.endWait + serviceTime;
    }

    Customer wait(double endWait) {
        return new Customer(this.id, this.arrTime, endWait);
    }

    double waitTime() {
        return this.endWait - this.arrTime;
    }

    @Override
    public int compareTo(Customer other) {
        if (this.arrTime < other.arrTime) {
            return -1;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "customer " + this.id;
    }
}
