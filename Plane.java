public class Plane implements Means {
    String flightNumber;
    String builderName;
    int maxTravelers;
    int totalTravelers;
    float maxWeight;
    float totalWeight;



    public Plane(String flightNumber, String builderName, int maxTravelers, float maxWeight) {
        this.flightNumber = flightNumber;
        this.builderName = builderName;
        this.maxTravelers = maxTravelers;
        this.maxWeight = maxWeight;
        this.totalTravelers = 0;
        this.totalWeight = 0.0f;
    }
    public String getFlightNumber() {
        return this.flightNumber;
    }

    public String getbuilderName() {
        return builderName;
    }

    public int getMaxTravelers() {
        return maxTravelers;
    }

    public int getTotalTravelers() {
        return totalTravelers;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public float getTotalWeight() {
        return totalWeight;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setbuilderName(String builderName) {
        this.builderName = builderName;
    }

    public void setMaxTravelers(int maxTravelers) {
        this.maxTravelers = maxTravelers;
    }

    public void setTotalTravelers(int totalTravelers) {
        this.totalTravelers = totalTravelers;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }


    public void addTraveler(int travelers) {
        if (totalTravelers + travelers <= maxTravelers) {
            totalTravelers += travelers;
        } else {
            System.out.println("Cannot add more travelers than the maximum capacity.");
        }
    }
    public void addWeight(float weight) {
        if (totalWeight + weight <= maxWeight) {
            totalWeight += weight;
        } else {
            System.out.println("Cannot add more weight than the maximum capacity.");
        }
    }

    @Override
    public String toString() {
        return "Plane{" +
                "flightNumber='" + flightNumber + '\'' +
                ", builderName='" + builderName + '\'' +
                ", maxTravelers=" + maxTravelers +
                ", totalTravelers=" + totalTravelers +
                ", maxWeight=" + maxWeight +
                ", totalWeight=" + totalWeight +
                '}';
    }
}
