interface Chargeable {
    double chargingFee(int units);
    default String chargingStatus() { return "Charging supported"; }
    static boolean validUnits(int units) { return units >= 0; }
}

abstract class AbstractVehicle {
    private final String regNo;
    AbstractVehicle(String regNo) { this.regNo = regNo; }
    String getRegNo() { return regNo; }
    abstract double parkingFee(int hours);
}

class RegularCar extends AbstractVehicle {
    RegularCar(String regNo) { super(regNo); }
    @Override double parkingFee(int hours) { return hours * 30.0; }
}

class EVCar extends RegularCar implements Chargeable {
    EVCar(String regNo) { super(regNo); }
    @Override public double chargingFee(int units) {
        if (!Chargeable.validUnits(units)) throw new IllegalArgumentException("Invalid units");
        return units * 12.0;
    }
}

public class AbstractionDemo {
    public static void main(String[] args) {
        EVCar ev = new EVCar("TN03EF3333");
        System.out.println("Registration: " + ev.getRegNo());
        System.out.println("Parking fee: Rs " + ev.parkingFee(2));
        System.out.println("Charging fee: Rs " + ev.chargingFee(5));
        System.out.println(ev.chargingStatus());
    }
}
