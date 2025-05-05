class RefuelingVehicle implements Means {
    String name;

    public RefuelingVehicle(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void fuelingPlane() {
        System.out.println("Refueling the plane...");

    }
    
}