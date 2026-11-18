class TicketRecord {
    private final int ticketId;
    private final String registration;
    private final int floor;
    private final int slot;

    TicketRecord() { this(0, "NOT-SET", 0, 0); }

    TicketRecord(int ticketId, String registration) {
        this(ticketId, registration, 0, 0);
    }

    TicketRecord(int ticketId, String registration, int floor, int slot) {
        if (registration == null || registration.isBlank() || floor < 0 || slot < 0)
            throw new IllegalArgumentException("Invalid ticket data");
        this.ticketId = ticketId;
        this.registration = registration;
        this.floor = floor;
        this.slot = slot;
    }

    TicketRecord(TicketRecord other) {
        this(other.ticketId, other.registration, other.floor, other.slot);
    }

    public String toString() {
        return "Ticket " + ticketId + " | " + registration +
               " | Floor " + floor + " | Slot " + slot;
    }
}

public class ConstructorsDemo {
    public static void main(String[] args) {
        TicketRecord t1 = new TicketRecord(1001, "TN01AB1111", 0, 2);
        TicketRecord t2 = new TicketRecord(t1);
        System.out.println(t1);
        System.out.println("Copied: " + t2);
    }
}
