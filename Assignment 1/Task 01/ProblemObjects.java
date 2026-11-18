import java.time.LocalDateTime;

class ParkingVehicle {
    String registrationNumber;
    String type;
    LocalDateTime entryTime;

    ParkingVehicle(String registrationNumber, String type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
        this.entryTime = LocalDateTime.now();
    }
}

class ParkingSlot {
    int floor;
    int slotNumber;
    ParkingVehicle vehicle;

    ParkingSlot(int floor, int slotNumber) {
        this.floor = floor;
        this.slotNumber = slotNumber;
    }

    boolean isFree() { return vehicle == null; }
}

class ParkingTicket {
    static int nextNumber = 1000;
    int ticketNumber;
    ParkingVehicle vehicle;

    ParkingTicket(ParkingVehicle vehicle) {
        this.ticketNumber = nextNumber++;
        this.vehicle = vehicle;
    }
}

public class ProblemObjects {
    public static void main(String[] args) {
        ParkingVehicle vehicle = new ParkingVehicle("TN01AB1111", "Car");
        ParkingSlot slot = new ParkingSlot(0, 1);
        slot.vehicle = vehicle;
        ParkingTicket ticket = new ParkingTicket(vehicle);

        System.out.println("Vehicle: " + vehicle.registrationNumber);
        System.out.println("Type: " + vehicle.type);
        System.out.println("Allocated slot: Floor " + slot.floor + ", Slot " + slot.slotNumber);
        System.out.println("Ticket number: " + ticket.ticketNumber);
        System.out.println("Slot occupied: " + !slot.isFree());
    }
}
