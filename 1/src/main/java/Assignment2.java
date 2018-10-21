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

    long start = 0, end = 0;

    private void init() {
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PW);
            if(!connection.isClosed()) {
                System.out.println("Succeed to connect...");


            }else{
                System.out.println("Fail to connect...");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void solution1() {
        try {
            Statement statement = connection.createStatement();
            start = System.currentTimeMillis();
            statement.executeQuery("select * from orders o where o.age < 20");
            end = System.currentTimeMillis();
            System.out.println("Solution 1 costs " + (end - start) + " milliseconds");
            statement.execute("CREATE INDEX age_index ON orders (age)");
            System.out.println("Succeed to create index on age...");
            start = System.currentTimeMillis();
            statement.executeQuery("select * from orders o where o.age < 20");
            end = System.currentTimeMillis();
            System.out.println("Solution 1+ costs " + (end - start) + " milliseconds");



        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Assignment2 assignment2 = new Assignment2();
        assignment2.init();
       // assignment2.solution1();
    }

    private long getResNumber(ResultSet resultSet) {
        try {
            long count = 0;
            while (resultSet.next()) {
                count += 1;
            }
            return count;
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

}
