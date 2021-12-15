import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
    public String name;
    public String sid;
    public String email;
    public String password;

    public Student() {
    }

    public Student(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Student(String name, String sid, String email, String password) {
        this.name = name;
        this.sid = sid;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sid='" + sid + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public Student(Connection conn, Statement stmt) {
        try {
            ResultSet rs = null;
            if (stmt.execute("SELECT COUNT(*) FROM student")) {
                rs = stmt.getResultSet();
            }
            int registeredStudent = 0;
            while (rs.next()) {
                registeredStudent = Integer.parseInt(rs.getString("COUNT(*)"));
            }
            // Create account if the number of registered student is less than 18
            if (registeredStudent > 17) {
                System.out.println("Registration full");
            } else {
                Scanner sc = new Scanner(System.in);
                System.out.println("Enter name");
                String studentName = sc.nextLine();
                System.out.println("Enter Student ID");
                String SID = sc.nextLine();
                System.out.println("Enter email");
                String email = sc.nextLine();
                System.out.println("Enter password");
                String password = sc.nextLine();
                String query = " insert into students (name, SID, email, password)" + " values (?, ?, ?, ?)";
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, studentName);
                preparedStmt.setString(2, SID);
                preparedStmt.setString(3, email);
                preparedStmt.setString(4, password);
                preparedStmt.execute();
                System.out.println("Registration successful");
            }
        } catch (Exception e) {
            System.out.println("Failed to create account " + "\nDuplicate SID or Email");
        }
    }

    public static void login(Connection conn, Statement stmt) {
        try {
            int id = 0;
            int section1 = 0;
            int section2 = 0;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter email");
            String Email = sc.nextLine();
            System.out.println("Enter password");
            String Password = sc.nextLine();
            String sql = "SELECT id from student WHERE student.email='" + Email + "' AND student.password='" + Password + "'";
            ResultSet rs = null;
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
            }
            while (rs.next()) {
                id = rs.getInt("id");
                System.out.println("Login successful");
            }
            if (stmt.execute("SELECT COUNT(*) FROM student WHERE section='1'")) {
                rs = stmt.getResultSet();
            }
            while (rs.next()) {
                section1 = rs.getInt("COUNT(*)");
            }
            if (stmt.execute("SELECT COUNT(*) FROM student WHERE section='2'")) {
                rs = stmt.getResultSet();
            }
            while (rs.next()) {
                section2 = rs.getInt("COUNT(*)");
            }
            System.out.println("1 Section-01 Sunday: 12:30 pm 1:00 pm      " + (9 - section1) + " Seats Remaining");
            System.out.println("2 Section-02 Sunday: 2:30 pm 4:00 pm      " + (9 - section2) + " Seats Remaining");
            System.out.println("Choose section");
            int section = Integer.parseInt(sc.nextLine());
            String query = "update student set student.section = ? where id = ?";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, section);
            preparedStmt.setInt(2, id);
            preparedStmt.executeUpdate();
            System.out.println("Section updated");
        } catch (Exception e) {
            System.out.println("Login Failed. Create account if you don't have any");
        }
    }
}
