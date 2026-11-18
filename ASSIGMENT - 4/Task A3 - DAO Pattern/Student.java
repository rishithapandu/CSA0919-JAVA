public class Student {
    public final int id;
    public final String name, regNo, course, email;
    public Student(int id, String name, String regNo, String course, String email) {
        this.id=id; this.name=name; this.regNo=regNo; this.course=course; this.email=email;
    }
    public String toString() { return id + " " + name + " " + regNo + " " + course + " " + email; }
}
