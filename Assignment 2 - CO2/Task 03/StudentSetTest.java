import java.util.*;
class Student {
    private final String regNo;
    Student(String regNo) { this.regNo = regNo; }
    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return Objects.equals(regNo, other.regNo);
    }
    @Override public int hashCode() { return Objects.hash(regNo); }
    @Override public String toString() { return regNo; }
}
public class StudentSetTest {
    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();
        students.add(new Student("STU101"));
        students.add(new Student("STU102"));
        students.add(new Student("STU101"));
        System.out.println("Set contents: " + students);
        System.out.println("Unique student count: " + students.size());
        System.out.println("Duplicate scan rejected: " +
            !students.add(new Student("STU102")));
        System.out.println("Contains STU101: " + students.contains(new Student("STU101")));
    }
}
