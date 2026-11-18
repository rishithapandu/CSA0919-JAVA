import java.io.*;
import java.util.*;

class Resident implements Serializable {
    private static final long serialVersionUID = 1L;
    String name, registerNo;
    transient String password;
    transient Object liveConnection; // runtime resource is deliberately not persisted
    Resident(String name, String registerNo, String password) {
        this.name=name; this.registerNo=registerNo; this.password=password;
    }
}
class Room implements Serializable {
    private static final long serialVersionUID = 1L;
    int roomNo; List<Resident> residents = new ArrayList<>();
    Room(int roomNo) { this.roomNo=roomNo; }
}
class Hostel implements Serializable {
    private static final long serialVersionUID = 1L;
    List<Room> rooms = new ArrayList<>();
}
public class ResidentSerializationDemo {
    public static void main(String[] args) throws Exception {
        Hostel h=new Hostel(); Room r=new Room(101);
        r.residents.add(new Resident("Anu","1924001","not-stored"));
        h.rooms.add(r);
        try (ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("hostel.dat"))) { out.writeObject(h); }
        try (ObjectInputStream in=new ObjectInputStream(new FileInputStream("hostel.dat"))) {
            Hostel copy=(Hostel)in.readObject(); Resident resident=copy.rooms.get(0).residents.get(0);
            System.out.println("Resident: "+resident.name+", Register: "+resident.registerNo);
            System.out.println("Password after deserialization: "+resident.password);
        }
    }
}
