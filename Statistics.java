class Statistics {
    private final double totalWT;
    private final int numServed;
    private final int numUnserved;

    Statistics(double totalWT, int numServed, int numUnserved) {
        this.totalWT = totalWT;
        this.numServed = numServed;
        this.numUnserved = numUnserved;
    }

    Statistics update(Pair<String, Double> stat) {
        if (stat.t().equals("s")) {
            return new Statistics(this.totalWT + stat.u(), this.numServed + 1, this.numUnserved);
        } else if (stat.t().equals("l")) {
            return new Statistics(this.totalWT, this.numServed, this.numUnserved + 1);
        }
        return this;
    }

    @Override
    public String toString() {
        String avgWT = String.format("%.3f", this.totalWT / Math.max(this.numServed, 1));
        return "[" + avgWT + " " + this.numServed + " " + this.numUnserved + "]";
    }
}
