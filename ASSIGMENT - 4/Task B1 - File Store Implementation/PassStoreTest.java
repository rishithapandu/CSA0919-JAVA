import java.nio.file.*;
import java.util.*;

public class PassStoreTest {
    public static void main(String[] args) throws Exception {
        Path p=Paths.get("test-passes.csv"); Files.deleteIfExists(p);
        PassStore store=new PassStore(p);
        store.add(new String[]{"P1003","1924003","Meena","15","North Stop","2027-06-30","500"});
        Files.writeString(p, "P1002,only-two-fields\n", StandardOpenOption.APPEND);
        System.out.println("Valid record found: "+(store.find("P1003")!=null));
        System.out.println("Missing record result: "+store.find("P9999"));
        System.out.println("Route 15 records: "+store.byRoute(15).size());
    }
}
