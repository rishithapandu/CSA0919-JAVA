import java.time.LocalTime;
import java.time.LocalDate;

interface ParkingPass {
    boolean isValid(LocalDate date);
}

class VisitorOneDayPass implements ParkingPass {
    private final LocalDate validDate;
    VisitorOneDayPass(LocalDate validDate) { this.validDate = validDate; }
    @Override public boolean isValid(LocalDate date) { return validDate.equals(date); }
}

abstract class NewVehicle {
    abstract double parkingFee(int minutes, LocalTime entryTime);
}

class EBike extends NewVehicle {
    @Override double parkingFee(int minutes, LocalTime entryTime) { return 20.0; }
}

class DeliveryVan extends NewVehicle {
    @Override double parkingFee(int minutes, LocalTime entryTime) {
        int hours = (minutes + 59) / 60;
        boolean peak = !entryTime.isBefore(LocalTime.of(9, 0)) &&
                       entryTime.isBefore(LocalTime.of(17, 0));
        return hours * 30.0 * (peak ? 2 : 1);
    }
}

public class DesignChallenge {
    public static void main(String[] args) {
        EBike bike = new EBike();
        DeliveryVan van = new DeliveryVan();
        VisitorOneDayPass pass = new VisitorOneDayPass(LocalDate.of(2026, 11, 18));

        System.out.println("E-bike flat fee: Rs " +
                bike.parkingFee(90, LocalTime.of(10, 0)));
        System.out.println("Delivery van peak fee for 2 hours: Rs " +
                van.parkingFee(120, LocalTime.of(10, 0)));
        System.out.println("Delivery van off-peak fee for 2 hours: Rs " +
                van.parkingFee(120, LocalTime.of(18, 0)));
        System.out.println("Visitor one-day pass valid: " +
                pass.isValid(LocalDate.of(2026, 11, 18)));
        int[] fiveUnequalFloors = {20, 35, 40, 25, 50};
        System.out.println("Five-floor capacity: " +
                java.util.Arrays.stream(fiveUnequalFloors).sum());
    }
}
