public class DynamicPricing 
{
    static final double BASE_FARE = 10.0;
    static final double PER_KM_RATE = 6.0;
    static final double PER_MIN_RATE = 2.0;

    public static double calculateFare(double distanceKm, double timeMinutes,
                                       int activeRequests, int availableDrivers,
                                       String trafficCondition)
    {
        double baseFare = BASE_FARE + (distanceKm * PER_KM_RATE) + (timeMinutes * PER_MIN_RATE);

        double surgeMultiplier = 1.0;
        if (activeRequests > availableDrivers && availableDrivers != 0) {
            surgeMultiplier += (double)(activeRequests - availableDrivers) / availableDrivers;
        }

        double trafficMultiplier = 1.0;
        switch (trafficCondition.toLowerCase()) 
        {
            case "moderate":
                trafficMultiplier = 1.2;
                break;
            case "heavy":
                trafficMultiplier = 1.5;
                break;
            case "light":
            default:
                trafficMultiplier = 1.0;
        }

        double finalFare = baseFare * surgeMultiplier * trafficMultiplier;

        return Math.round(finalFare * 100.0) / 100.0; // Rounded to 2 decimal places
    }

    public static void main(String[] args) 
    {
        double distanceKm = 12.5;
        double timeMinutes = 25;
        int activeRequests = 1;
        int availableDrivers = 15;
        String trafficCondition = "heavy";

        double fare = calculateFare(distanceKm, timeMinutes, activeRequests, availableDrivers, trafficCondition);

        System.out.println("Estimated Fare: " + fare);
    }
}
