import java.util.Objects;

abstract class FeeVehicle {
    private final String regNo;
    FeeVehicle(String regNo) { this.regNo = regNo; }
    abstract double rate();
    @Override public String toString() { return getClass().getSimpleName() + "[" + regNo + "]"; }
    @Override public boolean equals(Object obj) {
        return obj instanceof FeeVehicle other && regNo.equals(other.regNo);
    }
    @Override public int hashCode() { return Objects.hash(regNo); }
}

class FeeTwoWheeler extends FeeVehicle {
    FeeTwoWheeler(String regNo) { super(regNo); }
    @Override double rate() { return 10.0; }
}

class FeeCar extends FeeVehicle {
    FeeCar(String regNo) { super(regNo); }
    @Override double rate() { return 30.0; }
}

public class OverridingDemo {
    public static void main(String[] args) {
        FeeVehicle bike = new FeeTwoWheeler("TN01A1");
        FeeVehicle car = new FeeCar("TN02B2");
        System.out.println(bike + " rate = Rs " + bike.rate());
        System.out.println(car + " rate = Rs " + car.rate());
        System.out.println("Same registration objects equal: " +
                new FeeCar("TN02B2").equals(new FeeTwoWheeler("TN02B2")));
    }
}
