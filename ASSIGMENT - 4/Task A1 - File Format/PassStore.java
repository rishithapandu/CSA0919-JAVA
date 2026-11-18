import java.io.*;
import java.nio.file.*;
import java.util.*;

public class PassStore {
    private final Path file;
    public PassStore(Path file) { this.file = file; }

    private String csv(String value) {
        String v = value == null ? "" : value;
        if (v.contains(",") || v.contains("\"") || v.contains("\n")) return "\"" + v.replace("\"", "\"\"") + "\"";
        return v;
    }
    private String[] parseCsv(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"' && quoted && i + 1 < line.length() && line.charAt(i + 1) == '"') { value.append('"'); i++; }
            else if (c == '"') quoted = !quoted;
            else if (c == ',' && !quoted) { fields.add(value.toString()); value.setLength(0); }
            else value.append(c);
        }
        fields.add(value.toString());
        if (quoted) throw new IllegalArgumentException("Unclosed CSV quote");
        return fields.toArray(new String[0]);
    }
    public void add(String[] p) throws IOException {
        if (p.length != 7 || p[0].isBlank() || p[1].isBlank()) throw new IllegalArgumentException("Required pass fields missing");
        Integer.parseInt(p[3]); Double.parseDouble(p[6]);
        Files.createDirectories(file.toAbsolutePath().getParent());
        try (BufferedWriter w = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            for (int i = 0; i < p.length; i++) { if (i > 0) w.write(","); w.write(csv(p[i])); }
            w.newLine();
        }
    }
    public String[] find(String passId) throws IOException {
        if (!Files.exists(file)) return null;
        try (BufferedReader r = Files.newBufferedReader(file)) {
            String line;
            while ((line = r.readLine()) != null) {
                try { String[] f = parseCsv(line); if (f.length == 7 && f[0].equals(passId)) return f; }
                catch (RuntimeException ex) { System.err.println("Skipped damaged record: " + ex.getMessage()); }
            }
        }
        return null;
    }
    public List<String[]> byRoute(int route) throws IOException {
        List<String[]> result = new ArrayList<>();
        if (!Files.exists(file)) return result;
        try (BufferedReader r = Files.newBufferedReader(file)) {
            String line;
            while ((line = r.readLine()) != null) try {
                String[] f = parseCsv(line);
                if (f.length == 7 && Integer.parseInt(f[3]) == route) result.add(f);
            } catch (RuntimeException ex) { System.err.println("Skipped invalid line: " + line); }
        }
        return result;
    }
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("passes.csv");
        Files.deleteIfExists(path);
        PassStore store = new PassStore(path);
        store.add(new String[]{"P1001","1924001","Anu, Priya","12","Central Stop","2027-03-31","450.0"});
        store.add(new String[]{"P1002","1924002","Ravi","12","Market Stop","2027-03-31","450.0"});
        System.out.println("Found P1001: " + Arrays.toString(store.find("P1001")));
        System.out.println("Route 12 count: " + store.byRoute(12).size());
    }
}
