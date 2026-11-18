import java.util.*;
public class PharmacyMapDemo {
    public static void main(String[] args) {
        Map<String, Integer> hash = new HashMap<>();
        Map<String, Integer> sorted = new TreeMap<>();
        Map<String, Integer> insertion = new LinkedHashMap<>();
        hash.put("MED104", 80); hash.put("MED101", 120); hash.put("MED110", 60);
        sorted.putAll(hash); insertion.put("MED104",80);
        insertion.put("MED101",120); insertion.put("MED110",60);
        System.out.println("HashMap: " + hash);
        System.out.println("TreeMap: " + sorted);
        System.out.println("LinkedHashMap: " + insertion);
        System.out.println("Stock for MED101: " + hash.get("MED101"));
    }
}
