
import java.util.Scanner;
import java.sql.*;



public class Main {
    public static void main(String[] args) throws Exception {
        //total seat for each seciton
        int sec01Seat = 9;
        int sec02Seat = 9;

        //jdbc global connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse310", "root", "7066");
        Statement statement = connection.createStatement();
//
//        //resultSet for section 01 starts
//        ResultSet sec01Rs = null;
//        if (statement.execute("SELECT COUNT(*) FROM section WHERE section = '1' ")) {
//            sec01Rs = statement.getResultSet();
//        }
//        int sec01 = 0;
//        while (sec01Rs.next()) {
//            sec01 = Integer.parseInt(sec01Rs.getString("COUNT(*)"));
//        }
//        //resultSet for section 01 ends
//
//        //resultSet for section 02 starts
//        ResultSet sec02Rs = null;
//        if (statement.execute("SELECT COUNT(*) FROM section WHERE section = '2' ")) {
//            sec02Rs = statement.getResultSet();
//        }
//        int sec02 = 0;
//        while (sec01Rs.next()) {
//            sec02 = Integer.parseInt(sec02Rs.getString("COUNT(*)"));
//        }
//        //resultSet for section 02 ends

        Scanner sc = new Scanner(System.in);

        //taking to main menu
        System.out.println("***************************************");
        System.out.println("Student Registration? Input: 1");
        System.out.println("Faculty Login? Input: 2");
        System.out.println("Student Login? Input: 3");



        System.out.println("***************************************");

        int choice = sc.nextInt();
        sc.nextLine();



        //new student registration by choosing 1
        if (choice == 1){


            //student information for registration
            System.out.print("Student name: ");
            String s_name = sc.next();
            System.out.print("SID: ");
            int sid = sc.nextInt();
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();



            //inserting to database
            try {
                String query = " insert into students (name, sid, email, password)" + " values (?, ?, ?, ?)";

                PreparedStatement preparedStmt = connection.prepareStatement(query);
                preparedStmt.setString(1, s_name);
                preparedStmt.setInt(2, sid);
                preparedStmt.setString(3, email);
                preparedStmt.setString(4, pass);
                preparedStmt.execute();

                System.out.println("Registration successful");

                connection.close();



            } catch (SQLException e) {
                e.printStackTrace();
            }


        //student login by choosing 03
        }else if (choice == 3){

            //credentials for login
            System.out.print("Email: ");
            String email = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();



            try {
            //fetching data from database
            ResultSet rs = statement.executeQuery("select * from students where email ='" + email + "' and password='" + pass + "'");

            if (rs.next()) {
                System.out.println("\n");



                System.out.println("--------------------------------------");
                System.out.println("Please wait!");
                System.out.println("Logging in...");
                int sec01 = 2;
                int sec02 = 0;
                System.out.println("--------------------------------------");

                //workfield

                //workfield

                   System.out.println("1 Section-01 Sunday: 12:30 pm 1:00 pm " + (sec01Seat - sec01) + " remianing");
                   System.out.println("2 Section-02 Sunday:  2:30 pm 4:00 pm " + (sec02Seat - sec02) + " remianing");
                   System.out.println("--------------------------------------");
                if (sec01 != 0 && sec02 != 9) {
                    System.out.println("Select Section: ");
                    int secNum = sc.nextInt();

                    if (secNum == 1){

                        String query = "UPDATE students SET section = '" + secNum  + "' WHERE email = '" + email + "'" ;

                        PreparedStatement preparedStmt = connection.prepareStatement(query);

                        preparedStmt.execute();

                        System.out.println("Section " + secNum + " Enrolled ");

                        connection.close();

                        System.out.println("1 Section-01 Sunday: 12:30 pm 1:00 pm " + (sec01Seat - sec01 - 1) + " remianing");
                        System.out.println("2 Section-02 Sunday:  2:30 pm 4:00 pm " + (sec02Seat - sec02) + " remianing");
                    }else {
                        String query = "UPDATE students SET section = '" + secNum  + "' WHERE email = '" + email + "'" ;

                        PreparedStatement preparedStmt = connection.prepareStatement(query);

                        preparedStmt.execute();

                        System.out.println("Section " + secNum + " Enrolled ");

                        connection.close();


                        System.out.println("1 Section-01 Sunday: 12:30 pm 1:00 pm " + (sec01Seat - sec01) + " remianing");
                        System.out.println("2 Section-02 Sunday:  2:30 pm 4:00 pm " + (sec02Seat - sec02 - 1) + " remianing");
                    }

                } else {
                    System.out.println("Seat filled up");
                }
            }else
                System.out.println("Invalid user name and password");
                connection.close();

                // ResultSet all = statement.executeQuery("SELECT * FROM students");



            } catch (SQLException e) {
                e.printStackTrace();
            }

        //login as faculty by choosing 2
        }else if (choice == 2){
            System.out.print("Faculty's Email: ");
            String email = sc.next();
            System.out.print("Password: ");
            String pass = sc.next();



            try {

                //fetching data from database
                ResultSet rs = statement.executeQuery("select * from faculty where email ='" + email + "' and password='" + pass + "'");
                if (rs.next()) {
                    System.out.println("Welcome, Respected Teacher! ");
                    System.out.println("\n");

                    System.out.println("--------------------------------------");
                    System.out.println("May I know which seciton do you wanna check?");
                    int sec = sc.nextInt();
                    System.out.println("Checking...");
                    System.out.println("\n");

                    if (sec == 1){
                        String sql = "SELECT name, sid FROM students WHERE section = '" + sec + "'";
                        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse310", "root", "7066");
                             Statement stmt  = conn.createStatement();
                             ResultSet res    = stmt.executeQuery(sql)) {

                            // loop through the result set
                            while (res.next()) {
                                System.out.println("Name" + "\t" +
                                        "sid");
                                System.out.println(res.getString("name") + "\t" +
                                        res.getString("sid"));

                            }
                        } catch (SQLException ex) {
                            System.out.println(ex.getMessage());

                        }
                    }








                }else
                    System.out.println("Invalid user name and password");
                connection.close();


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else {
            //handling error for unwanted inputs
            System.out.println("Plese insert inputs in between of 1-3.");
        }



    }

}

