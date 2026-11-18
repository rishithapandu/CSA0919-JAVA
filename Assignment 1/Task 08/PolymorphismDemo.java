abstract class PolyVehicle {
    abstract double ratePerHour();

    double calculateFee(int minutes) {
        int hours = (minutes + 59) / 60;
        return hours * ratePerHour();
    }

    double calculateFee(double hours) {
        return hours * ratePerHour();
    }
}

class PolyBike extends PolyVehicle {
    @Override double ratePerHour() { return 10; }
}
class PolyCar extends PolyVehicle {
    @Override double ratePerHour() { return 30; }
}
class PolyElectricCar extends PolyCar { }

public class PolymorphismDemo {
    public static void main(String[] args) {
        PolyVehicle[] vehicles = {new PolyBike(), new PolyCar(), new PolyElectricCar()};
        for (PolyVehicle v : vehicles)
            System.out.println(v.getClass().getSimpleName() +
                    " fee for 130 minutes = Rs " + v.calculateFee(130));
        System.out.println("Overloaded method for 2.5 hours = Rs " +
                new PolyCar().calculateFee(2.5));
    }
}
