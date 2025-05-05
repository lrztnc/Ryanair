public class Main {
    public static void main(String[] args) {
        // Costruzione delle strutture
        Runway runway = new Runway(1);
        ParkingArea parking = new ParkingArea();
        Hangar hangar = new Hangar();

        LuggageVehicle luggageVehicle = new LuggageVehicle(2000.0f);
        RefuelingVehicle refuelVehicle = new RefuelingVehicle("Refueler1");
        Plane plane1 = new Plane("AZ123", "Boeing 737", 180, 22000.0f);
        Plane plane2 = new Plane("BA456", "Airbus A320", 150, 18000.0f);

        // Gestione dei voli
        managePlane(plane1, runway, parking, hangar, luggageVehicle, refuelVehicle, 1200.0f);
        System.out.println();
        managePlane(plane2, runway, parking, hangar, luggageVehicle, refuelVehicle, 800.0f);

    }

    private static void managePlane(Plane plane,Runway runway,ParkingArea parking,Hangar hangar,LuggageVehicle luggageVehicle,RefuelingVehicle refuelVehicle,float baggageWeight) {
        System.out.println("=== Gestione del volo " + plane.getFlightNumber() + " ===");

        // Atterraggio
        if (runway.assignPlane(plane)) {
            System.out.println("Atterraggio su pista " + runway.getId());
        } else {
            System.out.println("La pista " + runway.getId() + " non è disponibile per il volo " + plane.getFlightNumber());
        }
        runway.clearRunway();

        // Parcheggio
        if (!parking.parkPlane(plane)) {
            System.out.println("Impossibile parcheggiare il volo " + plane.getFlightNumber());
        }

        // Hangaraggio
        if (!hangar.storePlane(plane)) {
            System.out.println("Impossibile stoccare il volo " + plane.getFlightNumber() + " in hangar");
        }

        // Carico bagagli
        luggageVehicle.chargePlane();

        // Rifornimento carburante
        refuelVehicle.fuelingPlane();
    }
}
