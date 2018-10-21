import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Assignment2 {
    Connection connection;
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/ERP?useUnicode=" +
            "true&characterEncoding=utf-8&useSSL=false";
    private final static String USER = "root";
    private final static String PW = "1212hjkl";
}
