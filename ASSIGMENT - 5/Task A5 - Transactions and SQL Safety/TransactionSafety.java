import java.sql.*;

public class TransactionSafety {
    public static void transfer(Connection con, int fromWallet, int toWallet, double amount) throws SQLException {
        boolean oldAutoCommit=con.getAutoCommit();
        con.setAutoCommit(false);
        try {
            try (PreparedStatement debit=con.prepareStatement(
                    "UPDATE wallet SET balance=balance-? WHERE wallet_id=? AND balance>=?");
                 PreparedStatement credit=con.prepareStatement(
                    "UPDATE wallet SET balance=balance+? WHERE wallet_id=?")) {
                debit.setDouble(1,amount); debit.setInt(2,fromWallet); debit.setDouble(3,amount);
                if (debit.executeUpdate()!=1) throw new SQLException("Insufficient balance or source wallet missing");
                credit.setDouble(1,amount); credit.setInt(2,toWallet);
                if (credit.executeUpdate()!=1) throw new SQLException("Destination wallet missing");
            }
            con.commit();
        } catch (SQLException ex) {
            con.rollback(); throw ex;
        } finally { con.setAutoCommit(oldAutoCommit); }
    }
    public static PreparedStatement safeLogin(Connection con, String id, String password) throws SQLException {
        PreparedStatement ps=con.prepareStatement("SELECT * FROM users WHERE id=? AND pwd=?");
        ps.setString(1,id); ps.setString(2,password); return ps;
    }
    public static void main(String[] args) {
        System.out.println("Use transfer() with a configured JDBC Connection.");
        System.out.println("Login queries use PreparedStatement parameters, not SQL concatenation.");
    }
}
