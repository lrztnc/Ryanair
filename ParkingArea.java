import java.util.ArrayList;
import java.util.List;

public class ParkingArea {
    private final int maxCapacity = 3; 
    private List<Plane> parkedPlanes;

    public ParkingArea() {
        this.parkedPlanes = new ArrayList<>();
    }

  
    public boolean parkPlane(Plane plane) {
        if (parkedPlanes.size() < maxCapacity) {
            parkedPlanes.add(plane);
            System.out.println("Plane " + plane.getFlightNumber() + " parked successfully.");
            return true;
        } else {
            System.out.println("Parking area is full. Plane " + plane.getFlightNumber() + " cannot be parked.");
            return false;
        }
    }

   
    public boolean removePlane(Plane plane) {
        if (parkedPlanes.contains(plane)) {
            parkedPlanes.remove(plane);
            System.out.println("Plane " + plane.getFlightNumber() + " removed from parking area.");
            return true;
        } else {
            System.out.println("Plane " + plane.getFlightNumber() + " is not in the parking area.");
            return false;
        }
    }

    
    public int getAvailableSpots() {
        return maxCapacity - parkedPlanes.size();
    }

    public boolean isFull() {
        return parkedPlanes.size() >= maxCapacity;
    }
   
    public List<Plane> getParkedPlanes() {
        return new ArrayList<>(parkedPlanes);
    }
}
