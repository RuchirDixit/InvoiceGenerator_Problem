public class InvoiceSummary {

    public final int numOfRides;
    public final double totalFare;
    public final double averagefare;

    public InvoiceSummary(int numOfRides, double totalFare) {
        this.numOfRides = numOfRides;
        this.totalFare = totalFare;
        this.averagefare = this.totalFare / this.numOfRides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) o;
        return numOfRides == that.numOfRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averagefare, averagefare) == 0;
    }
}
