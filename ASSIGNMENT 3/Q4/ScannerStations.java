class Station extends Thread {
    private final String stationName;
    private final int scripts;
    Station(String name, int scripts) { stationName = name; this.scripts = scripts; }
    public void run() {
        try {
            Thread.sleep(scripts * 100L);
            System.out.println(stationName + " scanned " + scripts + " scripts.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class ScannerStations {
    public static void main(String[] args) throws InterruptedException {
        Station[] stations = {
            new Station("Station 1", 1), new Station("Station 2", 2),
            new Station("Station 3", 3), new Station("Station 4", 4)
        };
        for (Station s : stations) s.start();
        for (Station s : stations) s.join();
        System.out.println("All stations finished.");
    }
}
