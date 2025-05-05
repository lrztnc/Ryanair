
import java.util.concurrent.Semaphore;

public class Main {

    private static final Semaphore serviceSemaphore = new Semaphore(1);
    private static final Semaphore runwaySemaphore = new Semaphore(1);

    public static void main(String[] args) {
        Hangar hangar = new Hangar();
        ParkingArea parking = new ParkingArea();
        Runway runway = new Runway(1);

        RefuelingVehicle refueler = new RefuelingVehicle("Refueler1");
        LuggageVehicle lugger = new LuggageVehicle(2000f);

        
        Plane p1 = new Plane("AZ123", "Boeing 737", 3, 500f);
        Plane p2 = new Plane("BA456", "Airbus A320", 2, 300f);

        hangar.storePlane(p1);
        hangar.storePlane(p2);

        new PlaneTask(p1, false, hangar, parking, runway, refueler, lugger).run();
        System.out.println();
        new PlaneTask(p2, true,  hangar, parking, runway, refueler, lugger).run();
    }

    static class PlaneTask implements Runnable {
        private final Plane plane;
        private final boolean roundTrip;
        private final Hangar hangar;
        private final ParkingArea parking;
        private final Runway runway;
        private final RefuelingVehicle refueler;
        private final LuggageVehicle lugger;

        PlaneTask(Plane plane, boolean roundTrip,
                  Hangar hangar, ParkingArea parking, Runway runway,
                  RefuelingVehicle refueler, LuggageVehicle lugger) {
            this.plane = plane;
            this.roundTrip = roundTrip;
            this.hangar = hangar;
            this.parking = parking;
            this.runway = runway;
            this.refueler = refueler;
            this.lugger = lugger;
        }

        @Override
        public void run() {
            try {
                System.out.println("\n--- Preparazione partenza " + plane.getFlightNumber() + " ---");
                hangar.removePlane(plane);

                while (!parking.parkPlane(plane)) {
                    Thread.sleep(100);
                }

                System.out.println("  > Carico passeggeri su " + plane.getFlightNumber());
                while (plane.getTotalTravelers() < plane.getMaxTravelers()) {
                    plane.addTraveler(1);
                    Thread.sleep(50);
                }
                System.out.println("  > Passeggeri caricati su " + plane.getFlightNumber());

                serviceSemaphore.acquire();
                System.out.println("  > Rifornimento su " + plane.getFlightNumber());
                refueler.fuelingPlane();
                serviceSemaphore.release();

                serviceSemaphore.acquire();
                System.out.println("  > Carico bagagli su " + plane.getFlightNumber());
                while (plane.getTotalWeight() < plane.getMaxWeight()) {
                    plane.addWeight(50f);
                    Thread.sleep(50);
                }
                System.out.println("  > Fine bagagli su " + plane.getFlightNumber());
                serviceSemaphore.release();

                System.out.println("  > Pronto al decollo: " + plane);

                runwaySemaphore.acquire();
                if (parking.removePlane(plane) && runway.assignPlane(plane)) {
                    System.out.println("  > " + plane.getFlightNumber() + " decolla");
                    runway.clearRunway();
                }
                runwaySemaphore.release();


                
                System.out.println("\n--- Atterraggio " + plane.getFlightNumber() + " ---");
                runwaySemaphore.acquire();
                if (runway.assignPlane(plane)) {
                    System.out.println("  > " + plane.getFlightNumber() + " atterrato");
                    runway.clearRunway();
                } else {
                    System.out.println("  > Pista occupata: " + plane.getFlightNumber() + " rimane in volo");
                }
                runwaySemaphore.release();

                
                if (!parking.parkPlane(plane)) {
                    System.out.println("  > Parcheggio pieno, " + plane.getFlightNumber() + " rimane in aria");
                    return;
                }

                plane.setTotalTravelers(0);
                plane.setTotalWeight(0f);
                System.out.println("  > Scarico completato su " + plane.getFlightNumber());

                if (!roundTrip) {
                    parking.removePlane(plane);
                    hangar.storePlane(plane);
                    System.out.println("  > " + plane.getFlightNumber() + " tornato in hangar");
                } else {
                    System.out.println("  > " + plane.getFlightNumber() + " pronto per ritorno");
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}