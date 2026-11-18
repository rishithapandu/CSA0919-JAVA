import java.util.*;
class Patient {
    final String name; final int severity; final long arrival;
    Patient(String name, int severity, long arrival) {
        this.name = name; this.severity = severity; this.arrival = arrival;
    }
    public String toString() { return name + " (severity " + severity + ")"; }
}
public class HospitalTriage {
    private final PriorityQueue<Patient> queue = new PriorityQueue<>(
        Comparator.comparingInt((Patient p) -> p.severity)
                  .thenComparingLong(p -> p.arrival));
    void admit(Patient p) { queue.add(p); }
    Patient serveNext() { return queue.poll(); }
    public static void main(String[] args) {
        HospitalTriage triage = new HospitalTriage();
        triage.admit(new Patient("Arun", 3, 1));
        triage.admit(new Patient("Bala", 1, 2));
        triage.admit(new Patient("Charan", 3, 3));
        triage.admit(new Patient("Divya", 2, 4));
        System.out.println("Treatment order:");
        Patient p;
        while ((p = triage.serveNext()) != null) System.out.println(p);
    }
}
