public class StringBuilderRedesign {
    static String original(String[] fields) {
        String ticket = "";
        for (String field : fields) ticket += field;
        return ticket;
    }
    static String redesigned(String[] fields) {
        StringBuilder ticketBuilder = new StringBuilder();
        for (String field : fields) ticketBuilder.append(field);
        return ticketBuilder.toString();
    }
    public static void main(String[] args) {
        String[] fields = {"PNR=12345 ", "Passenger=Anu ", "Coach=S2 ",
                           "Berth=21 ", "Fare=450 ", "Route=Chennai-Bengaluru"};
        String oldTicket = original(fields);
        String newTicket = redesigned(fields);
        System.out.println("Original ticket: " + oldTicket);
        System.out.println("Redesigned ticket: " + newTicket);
        System.out.println("Contents identical: " + oldTicket.equals(newTicket));
    }
}
