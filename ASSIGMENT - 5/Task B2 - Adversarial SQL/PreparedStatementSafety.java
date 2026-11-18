import java.sql.*;

public class PreparedStatementSafety {
    public static boolean login(Connection con, String userInput, String password) throws SQLException {
        String sql="SELECT COUNT(*) FROM users WHERE id=? AND pwd=?";
        try (PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,userInput); ps.setString(2,password);
            try (ResultSet rs=ps.executeQuery()) { return rs.next() && rs.getInt(1)>0; }
        }
    }
    public static void main(String[] args) {
        System.out.println("This test requires a local JDBC database and a users(id,pwd) table.");
        System.out.println("The user input is bound as a value and cannot change SQL query structure.");
    }
}
