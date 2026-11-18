class EncapsulatedSlot {
    private final int floor;
    private final int slotNumber;
    private String vehicleNumber;

    EncapsulatedSlot(int floor, int slotNumber) {
        if (floor < 0 || slotNumber < 0) throw new IllegalArgumentException("Invalid slot");
        this.floor = floor;
        this.slotNumber = slotNumber;
    }

    public void park(String vehicleNumber) {
        if (vehicleNumber == null || vehicleNumber.isBlank())
            throw new IllegalArgumentException("Vehicle number required");
        if (this.vehicleNumber != null) throw new IllegalStateException("Slot already occupied");
        this.vehicleNumber = vehicleNumber;
    }

    public void release() {
        if (vehicleNumber == null) throw new IllegalStateException("Slot is already free");
        vehicleNumber = null;
    }

    public String getStatus() {
        return "Floor " + floor + ", Slot " + slotNumber + ": " +
                (vehicleNumber == null ? "FREE" : vehicleNumber);
    }
}

public class EncapsulationDemo {
    public static void main(String[] args) {
        EncapsulatedSlot slot = new EncapsulatedSlot(0, 3);
        System.out.println(slot.getStatus());
        slot.park("TN01AB1111");
        System.out.println(slot.getStatus());
        slot.release();
        System.out.println(slot.getStatus());
    }
}
