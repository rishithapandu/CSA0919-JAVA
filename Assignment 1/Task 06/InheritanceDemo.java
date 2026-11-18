abstract class BaseVehicle {
    protected String registration;
    BaseVehicle(String registration) {
        this.registration = registration;
        System.out.println("Vehicle constructor");
    }
    abstract double hourlyRate();
}

class ParkingCar extends BaseVehicle {
    ParkingCar(String registration) {
        super(registration);
        System.out.println("Car constructor");
    }
    @Override double hourlyRate() { return 30.0; }
}

class ParkingElectricCar extends ParkingCar {
    ParkingElectricCar(String registration) {
        super(registration);
        System.out.println("ElectricCar constructor");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {
        ParkingElectricCar ev = new ParkingElectricCar("TN03EF3333");
        System.out.println("Registration: " + ev.registration);
        System.out.println("Hourly rate: Rs " + ev.hourlyRate());
    }
}
