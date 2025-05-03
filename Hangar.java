import java.util.ArrayList;
import java.util.List;

public class Hangar {
    private final int maxCapacity = 5; 
    private List<Plane> storedPlanes;

    public Hangar() {
        this.storedPlanes = new ArrayList<>();
    }

    public boolean storePlane(Plane plane) {
        if (storedPlanes.size() < maxCapacity) {
            storedPlanes.add(plane);
            System.out.println("Plane " + plane.getFlightNumber() + " stored in the hangar.");
            return true;
        } else {
            System.out.println("Hangar is full. Plane " + plane.getFlightNumber() + " cannot be stored.");
            return false;
        }
    }

    public boolean removePlane(Plane plane) {
        if (storedPlanes.contains(plane)) {
            storedPlanes.remove(plane);
            System.out.println("Plane " + plane.getFlightNumber() + " removed from the hangar.");
            return true;
        } else {
            System.out.println("Plane " + plane.getFlightNumber() + " is not in the hangar.");
            return false;
        }
    }

    public int getAvailableSpots() {
        return maxCapacity - storedPlanes.size();
    }

    public boolean isFull() {
        return storedPlanes.size() >= maxCapacity;
    }

    public List<Plane> getStoredPlanes() {
        return new ArrayList<>(storedPlanes);
    }
}