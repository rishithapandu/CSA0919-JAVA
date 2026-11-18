import java.util.Arrays;

class SlotAllocator {
    private final String[][] slots;

    SlotAllocator(int[] capacity) {
        slots = new String[capacity.length][];
        for (int i = 0; i < capacity.length; i++) slots[i] = new String[capacity[i]];
    }

    int[] allocate(String vehicle) {
        for (int f = 0; f < slots.length; f++) {
            for (int s = 0; s < slots[f].length; s++) {
                if (slots[f][s] == null) {
                    slots[f][s] = vehicle;
                    return new int[]{f, s};
                }
            }
        }
        return null;
    }

    boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length) return false;
        if (slot < 0 || slot >= slots[floor].length) return false;
        if (slots[floor][slot] == null) return false;
        slots[floor][slot] = null;
        return true;
    }
}

public class ControlLogic {
    static double calculateFee(int minutes, double rate) {
        if (minutes < 0) throw new IllegalArgumentException("Negative duration");
        int hours = (minutes + 59) / 60;
        return hours * rate;
    }

    public static void main(String[] args) {
        SlotAllocator p = new SlotAllocator(new int[]{2, 1});
        System.out.println(Arrays.toString(p.allocate("TN01AB1111")));
        System.out.println(Arrays.toString(p.allocate("TN02CD2222")));
        System.out.println(Arrays.toString(p.allocate("TN03EF3333")));
        System.out.println(p.allocate("TN04GH4444"));
        System.out.println(p.release(0, 1));
        System.out.println(Arrays.toString(p.allocate("TN04GH4444")));
        System.out.println("Fee for 75 minutes at Rs 30/hour: Rs " + calculateFee(75, 30));
    }
}
