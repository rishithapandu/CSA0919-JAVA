import java.sql.*;
import java.util.*;

public class BatchInsert {
    public static int loadAll(Connection con, List<String[]> passes) throws SQLException {
        String sql="INSERT INTO bus_pass(pass_id,reg_no,name,route_no,amount) VALUES(?,?,?,?,?)";
        boolean old=con.getAutoCommit(); con.setAutoCommit(false); int count=0;
        try (PreparedStatement ps=con.prepareStatement(sql)) {
            for (String[] p:passes) {
                ps.setString(1,p[0]); ps.setString(2,p[1]); ps.setString(3,p[2]);
                ps.setInt(4,Integer.parseInt(p[3])); ps.setDouble(5,Double.parseDouble(p[4]));
                ps.addBatch(); count++;
                if (count%1000==0) ps.executeBatch();
            }
            ps.executeBatch(); con.commit(); return count;
        } catch (SQLException | RuntimeException ex) {
            con.rollback(); throw ex;
        } finally { con.setAutoCommit(old); }
    }
    public static void main(String[] args) {
        System.out.println("Batch loader ready. Configure JDBC connection and bus_pass table to execute.");
    }
}
