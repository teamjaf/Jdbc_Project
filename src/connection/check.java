package connection;

import java.sql.*;
import javax.sql.*;

public class check  {
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "7066";
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cse310";



//    public void connection(String[] args)  {
//        try {
//
//            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cse310", "root", "7066");
//            Statement statement = connection.createStatement();
//
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");
//
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getString("name"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }


    public static Connection getDatabaseConnection() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }


}
