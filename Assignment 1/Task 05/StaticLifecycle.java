class TicketCounter {
    private static int nextTicket = 5000;
    private final int ticketNumber;

    TicketCounter() { ticketNumber = nextTicket++; }

    static int nextNumberPreview() { return nextTicket; }

    int getTicketNumber() { return ticketNumber; }
}

public class StaticLifecycle {
    public static void main(String[] args) {
        TicketCounter t1 = new TicketCounter();
        TicketCounter t2 = new TicketCounter();
        System.out.println("Ticket 1: " + t1.getTicketNumber());
        System.out.println("Ticket 2: " + t2.getTicketNumber());
        System.out.println("Next ticket: " + TicketCounter.nextNumberPreview());

        t1 = null;
        System.out.println("Ticket 1 reference cleared; object may be garbage-collected.");
    }
}
