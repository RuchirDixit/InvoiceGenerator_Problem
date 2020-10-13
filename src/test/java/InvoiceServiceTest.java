import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceServiceTest {

    InvoiceService invoiceService = null;
    Ride[] rides = null;
    InvoiceSummary expectedInvoiceSummary = null;
    private RideRepository rideRepository = null;
    @Before
    public void setUp() throws Exception
    {
        invoiceService = new InvoiceService();
        rideRepository = new RideRepository();
        invoiceService.setRideRepository(rideRepository);
        rides = new Ride[]{
                new Ride(CabRide.NOMRAL,2,5),
                new Ride(CabRide.PREMIUM,0.1,1),
        };
        expectedInvoiceSummary = new InvoiceSummary(2,45.0);
    }
    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance, time);
        Assert.assertEquals(25,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance, time);
        Assert.assertEquals(5,fare,0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }


    @Test
    public void givenUserIdandRide_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        invoiceService.addRides(userId,rides);
        InvoiceSummary summary = invoiceService.getInvoiceSummary(userId);
        Assert.assertEquals(expectedInvoiceSummary,summary);
    }
}
