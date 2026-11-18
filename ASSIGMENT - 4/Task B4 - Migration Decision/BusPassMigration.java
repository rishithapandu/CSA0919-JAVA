import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.*;

public class BusPassMigration {
    public static int migrate(Path csv, Connection con, Path failures) throws Exception {
        boolean old=con.getAutoCommit(); con.setAutoCommit(false); int loaded=0, lineNo=0;
        String sql="INSERT INTO bus_pass(pass_id,reg_no,name,route_no,amount) VALUES(?,?,?,?,?)";
        try (BufferedReader reader=Files.newBufferedReader(csv);
             BufferedWriter rejected=Files.newBufferedWriter(failures);
             PreparedStatement ps=con.prepareStatement(sql)) {
            String line;
            while ((line=reader.readLine())!=null) {
                lineNo++;
                String[] f=line.split(",",-1);
                try {
                    if (f.length!=5) throw new IllegalArgumentException("Expected 5 fields");
                    int route=Integer.parseInt(f[3]); double amount=Double.parseDouble(f[4]);
                    ps.setString(1,f[0]); ps.setString(2,f[1]); ps.setString(3,f[2]);
                    ps.setInt(4,route); ps.setDouble(5,amount); ps.addBatch(); loaded++;
                    if (loaded%1000==0) ps.executeBatch();
                } catch (RuntimeException ex) {
                    rejected.write("Line "+lineNo+": "+ex.getMessage()+" | "+line); rejected.newLine();
                }
            }
            ps.executeBatch(); con.commit(); return loaded;
        } catch (Exception ex) { con.rollback(); throw ex; }
        finally { con.setAutoCommit(old); }
    }
    public static void main(String[] args) {
        System.out.println("Migration reads records, validates them, batches valid rows and reports rejected rows.");
        System.out.println("A JDBC connection and destination table are required for a live migration.");
    }
}
