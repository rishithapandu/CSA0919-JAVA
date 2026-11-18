import java.sql.*;
import java.util.*;

public class StudentDao implements Dao<Student, Integer> {
    private final Connection con;
    public StudentDao(Connection con) { this.con = con; }
    public void insert(Student s) throws SQLException {
        String sql="INSERT INTO student(student_id,name,reg_no,course,email) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setInt(1,s.id); ps.setString(2,s.name); ps.setString(3,s.regNo);
            ps.setString(4,s.course); ps.setString(5,s.email); ps.executeUpdate();
        }
    }
    private Student map(ResultSet rs) throws SQLException {
        return new Student(rs.getInt("student_id"),rs.getString("name"),rs.getString("reg_no"),
                           rs.getString("course"),rs.getString("email"));
    }
    public Student findById(Integer id) throws SQLException {
        try (PreparedStatement ps=con.prepareStatement("SELECT * FROM student WHERE student_id=?")) {
            ps.setInt(1,id); try (ResultSet rs=ps.executeQuery()) { return rs.next()?map(rs):null; }
        }
    }
    public List<Student> findAll() throws SQLException {
        List<Student> out=new ArrayList<>();
        try (PreparedStatement ps=con.prepareStatement("SELECT * FROM student"); ResultSet rs=ps.executeQuery()) {
            while(rs.next()) out.add(map(rs));
        }
        return out;
    }
    public void update(Student s) throws SQLException {
        try (PreparedStatement ps=con.prepareStatement("UPDATE student SET name=?,reg_no=?,course=?,email=? WHERE student_id=?")) {
            ps.setString(1,s.name); ps.setString(2,s.regNo); ps.setString(3,s.course); ps.setString(4,s.email);
            ps.setInt(5,s.id); ps.executeUpdate();
        }
    }
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement ps=con.prepareStatement("DELETE FROM student WHERE student_id=?")) {
            ps.setInt(1,id); ps.executeUpdate();
        }
    }
}
