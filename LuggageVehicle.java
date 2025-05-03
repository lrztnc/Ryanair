public class LuggageVehicle implements Means{
    float maxWeight;
    float totalWeight;

    public LuggageVehicle(float maxWeight) {
        this.maxWeight = maxWeight;
        this.totalWeight = 0.0f;
    }
    public float getMaxWeight() {
        return maxWeight;
    }
    public float getTotalWeight() {
        return totalWeight;
    }
    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }
    
    public void setTotalWeight(float totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void addWeight(float weight) {
        this.totalWeight += weight;
    }

    public void removeWeight(float weight) {
        this.totalWeight -= weight;
    }

}
