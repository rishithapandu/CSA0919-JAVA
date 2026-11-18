import java.util.HashMap;
import java.util.Map;

class ActiveParking {
    private final Map<String, Long> entryMinute = new HashMap<>();

    public void enter(String regNo, long minute) {
        if (regNo == null || regNo.isBlank()) throw new IllegalArgumentException("Invalid registration");
        if (entryMinute.containsKey(regNo)) throw new IllegalArgumentException("Duplicate vehicle");
        entryMinute.put(regNo, minute);
    }

    public double exit(String regNo, long minute, double hourlyRate) {
        Long start = entryMinute.remove(regNo);
        if (start == null) throw new IllegalArgumentException("Vehicle not found");
        long duration = minute - start;
        if (duration < 0) throw new IllegalArgumentException("Exit precedes entry");
        long hours = (duration + 59) / 60;
        return hours * hourlyRate;
    }

    public int occupancy() { return entryMinute.size(); }
}

public class ImplementationTesting {
    public static void main(String[] args) {
        ActiveParking p = new ActiveParking();
        p.enter("TN01AB1111", 0);
        p.enter("TN02CD2222", 0);
        System.out.println("Occupancy after entry: " + p.occupancy());
        System.out.println("Two-wheeler fee (120 min): Rs " +
                p.exit("TN01AB1111", 120, 10));
        System.out.println("Car fee (120 min): Rs " +
                p.exit("TN02CD2222", 120, 30));
        System.out.println("Occupancy after exit: " + p.occupancy());
        System.out.println("Expected daily collection: Rs 80.0");
    }
}
