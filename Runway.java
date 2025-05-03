public class Runway {

    private int id;                
    private Plane currentPlane; 

    public Runway(int id) {
        this.id = id;
        this.currentPlane = null; 
    }

    public int getId() {
        return id;
    }

   
    public boolean isAvailable() {
        return currentPlane == null;
    }

    
    public boolean assignPlane(Plane airplane) {
        if (isAvailable()) {
            this.currentPlane = airplane;
            return true;
        }
        return false; 
    }

   
    public void clearRunway() {
        this.currentPlane = null;
    }

   
    public Plane getCurrentPlane() {
        return currentPlane;
    }

    @Override
    public String toString() {
        if (currentPlane != null) {
            return "Runway " + id + " is occupied by flight " + currentPlane.getFlightNumber();
        } else {
            return "Runway " + id + " is available";
        }
    }
}