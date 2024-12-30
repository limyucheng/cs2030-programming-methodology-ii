import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.function.Supplier;
import java.util.Random;

class Main {
    private static final Random RNG_REST = new Random(3L);
    private static final Random RNG_REST_PERIOD = new Random(4L);
    private static double SERVER_REST_RATE = 0.1;

    static double genRestPeriod() {
        return -Math.log(RNG_REST_PERIOD.nextDouble()) / SERVER_REST_RATE;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int numOfSelfChecks = sc.nextInt();
        int qmax = sc.nextInt();
        double probRest = sc.nextDouble();
        int numOfCustomers = sc.nextInt();
        Supplier<Double> serviceTimes = new DefaultServiceTime();
        Supplier<Double> restTimes = () -> RNG_REST.nextDouble() < probRest ? genRestPeriod() : 0.0;

        List<Pair<Integer,Double>> arrivals = IntStream.rangeClosed(1, numOfCustomers)
            .mapToObj(i -> new Pair<Integer,Double>(i, sc.nextDouble()))
            .toList();

        Simulator sim = new Simulator(numOfServers, numOfSelfChecks, qmax, serviceTimes,
                restTimes, numOfCustomers, arrivals);
        System.out.println(sim.run());
    }
}
