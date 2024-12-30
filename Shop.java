import java.util.Optional;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.function.Supplier;

class Shop {
    private final List<Operator> operators;
    private final Supplier<Double> serviceTimes;
    private final Supplier<Double> restTimes;
    private final int qmax;
    private final int numInSelfCheckQ;

    Shop(int numOfServers, int numOfSelfChecks, Supplier<Double> serviceTimes,
        Supplier<Double> restTimes, int qmax) {
        this.operators = IntStream.iterate(1, i -> i <= numOfServers + numOfSelfChecks, i -> i + 1)
        .mapToObj(n -> n <= numOfServers ? new Server(n) : new SelfCheck(n))
        .toList();
        this.serviceTimes = serviceTimes;
        this.restTimes = restTimes;
        this.qmax = qmax;
        this.numInSelfCheckQ = 0;
    }

    private Shop(List<Operator> operators, Supplier<Double> serviceTimes,
        Supplier<Double> restTimes, int qmax, int numInSelfCheckQ) {
        this.operators = operators;
        this.serviceTimes = serviceTimes;
        this.restTimes = restTimes;
        this.qmax = qmax;
        this.numInSelfCheckQ = numInSelfCheckQ;
    }

    Optional<Operator> findOperator(Customer customer) {
        return this.operators.stream()
        .filter(op -> op.canServe(customer))
        .findFirst();
    }

    Optional<Operator> findQueue() {
        return this.operators.stream()
        .filter(op -> op.canWait(this.qmax, this.numInSelfCheckQ))
        .findFirst();
    }

    Shop queue() {
        return new Shop(this.operators, this.serviceTimes, this.restTimes,
            this.qmax, this.numInSelfCheckQ + 1);
    }

    Shop unqueue() {
        return new Shop(this.operators, this.serviceTimes, this.restTimes,
            this.qmax, Math.max(this.numInSelfCheckQ - 1, 0));
    }

    Shop update(Operator operator) {
        List<Operator> updated = IntStream.range(0, this.operators.size())
            .mapToObj(i -> this.operators.get(i).sameId(operator) ? operator :
                this.operators.get(i))
            .toList();
        return new Shop(updated, this.serviceTimes, this.restTimes,
            this.qmax, this.numInSelfCheckQ);
    }

    Operator getUpdatedOperator(Operator operator) {
        for (Operator op : this.operators) {
            if (op.sameId(operator)) {
                return op;
            }
        }
        return operator;
    }

    Operator getEarliestSelfCheck(Operator updatedOperator) {
        Operator earliestOp = updatedOperator;
        double earliestTime = updatedOperator.endServe();
        for (Operator op : this.operators) {
            if (!op.isHuman() && op.endServe() < earliestTime) {
                earliestOp = op;
                earliestTime = op.endServe();
            }
        }
        return earliestOp;
    }

    double getNumInSelfCheckQ() {
        return this.numInSelfCheckQ;
    }

    double getServiceTime() {
        return this.serviceTimes.get();
    }

    double getRestTime() {
        return this.restTimes.get();
    }

    @Override
    public String toString() {
        return this.operators.toString();
    }
}
