public class InvoiceService {
    private static final int COST_PER_TIME = 1;
    private static final double MINIMUM_COST_PER_KM = 10;
    private static final double MINIMUM_FARE = 5;
    private RideRepository rideRepositoy;

    public void setRideRepository(RideRepository rideRepositoy)
    {
        this.rideRepositoy = rideRepositoy;
    }
    public double calculateFare(double distance,int time)
    {
        double totalFare = distance * MINIMUM_COST_PER_KM + time * COST_PER_TIME;
        return Math.max(totalFare,MINIMUM_FARE);
    }
    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides)
        {
           // totalFare += this.calculateFare(ride.distance,ride.time);
            totalFare += ride.cabRide.calcCostOfCabRide(ride);
        }
        return new InvoiceSummary(rides.length,totalFare);
    }

    public void addRides(String userId, Ride[] rides) {
        rideRepositoy.addRides(userId,rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepositoy.getRides(userId));
    }
}
