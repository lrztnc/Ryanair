public class Main {
    public static void main(String[] args) {
        // plane declaration
        Plane p1 = new Plane("FL001", "Boeing", 100, 1000f);
        Plane p2 = new Plane("FL002", "Airbus", 150, 1500f);

        // add travelers and weight to p1
        p1.addTraveler(50);
        p1.addWeight(500f);
        System.out.println(p1);
        // add travelers and weight to p2
        p2.addTraveler(80);
        p2.addWeight(800f);
        System.out.println(p2);

        // runway operations
        // create two runways
        Runway r1 = new Runway(1);
        Runway r2 = new Runway(2);
        System.out.println(r1); // should be available
        if (r1.assignPlane(p1)) {
            System.out.println("Plane " + p1.getFlightNumber() + " assigned to runway " + r1.getId());
        }
        System.out.println(r1); // now occupied
        r1.clearRunway();
        System.out.println(r1); // available again

        //park the planes
        ParkingArea parking = new ParkingArea();
        if (parking.parkPlane(p1)) {
            System.out.println("Plane " + p1.getFlightNumber() + " parked in area (spots left: " +
                               parking.getAvailableSpots() + ")");
        }
        if (parking.parkPlane(p2)) {
            System.out.println("Plane " + p2.getFlightNumber() + " parked in area (spots left: " +
                               parking.getAvailableSpots() + ")");
        }
        parking.removePlane(p1);
        System.out.println("Removed " + p1.getFlightNumber() + " from parking (spots left: " +
                           parking.getAvailableSpots() + ")");

        // ==== Hangar operations ====
        Hangar hangar = new Hangar();
        if (hangar.storePlane(p1)) {
            System.out.println("Stored " + p1.getFlightNumber() + " in hangar (spots left: " +
                               hangar.getAvailableSpots() + ")");
        }
        System.out.println("Hangar full? " + hangar.isFull());

        // add luggage to the plane
        LuggageVehicle lugVeh = new LuggageVehicle(200f);
        lugVeh.addWeight(120f);
        System.out.println("LuggageVehicle loaded weight: " + lugVeh.getTotalWeight() +
                           " / " + lugVeh.getMaxWeight());
        lugVeh.removeWeight(20f);
        System.out.println("After unloading 20: total weight = " + lugVeh.getTotalWeight());

        // fuel the plane
        RefuelingVehicle refVeh = new RefuelingVehicle("Refuel-1");
        System.out.println("Refueling vehicle name: " + refVeh.getName());
        refVeh.setName("Refuel-2");
        System.out.println("Refueling vehicle renamed to: " + refVeh.getName());

        // check if the classes implement the Means interface
        Means m1 = p1;
        Means m2 = lugVeh;
        System.out.println("Plane implements Means? " + (m1 instanceof Means));
        System.out.println("LuggageVehicle implements Means? " + (m2 instanceof Means));
    }
}