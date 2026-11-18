public class TicketBenchmark {
    static String buildString(int fields) {
        String s = "";
        for (int i = 1; i <= fields; i++) s += "F" + i + "=VALUE ";
        return s;
    }
    static String buildBuilder(int fields) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= fields; i++) sb.append("F").append(i).append("=VALUE ");
        return sb.toString();
    }
    static String buildBuffer(int fields) {
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= fields; i++) sb.append("F").append(i).append("=VALUE ");
        return sb.toString();
    }
    static long measure(int fields, int tickets, int method) {
        long start = System.nanoTime();
        String result = "";
        for (int t = 0; t < tickets; t++) {
            if (method == 0) result = buildString(fields);
            else if (method == 1) result = buildBuilder(fields);
            else result = buildBuffer(fields);
        }
        long elapsed = System.nanoTime() - start;
        if (result.isEmpty()) System.out.println("No ticket generated");
        return elapsed;
    }
    public static void main(String[] args) {
        int[][] workloads = {{100, 8}, {10000, 50}, {20000, 100}};
        String[] names = {"String", "StringBuilder", "StringBuffer"};
        for (int[] w : workloads) {
            System.out.println("\\nTickets=" + w[0] + ", Fields/ticket=" + w[1]);
            for (int m = 0; m < 3; m++)
                System.out.println(names[m] + ": " + measure(w[1], w[0], m) / 1_000_000.0 + " ms");
        }
    }
}
