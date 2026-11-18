import java.io.*;

public class SalesReport {
    public static void main(String[] args) {
        String[] stores = {"store1.txt", "store2.txt", "store3.txt"};
        int total = 0;
        try (BufferedReader reader1 = new BufferedReader(new StringReader("100\n200"));
             BufferedReader reader2 = new BufferedReader(new StringReader("150\n250"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("summary.txt"))) {
            String line;
            while ((line = reader1.readLine()) != null) total += Integer.parseInt(line);
            while ((line = reader2.readLine()) != null) total += Integer.parseInt(line);
            writer.write("Total sales: " + total);
            writer.newLine();
            System.out.println("Report created.");
            System.out.println("Total sales: " + total);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Report error: " + e.getMessage());
        }
    }
}
