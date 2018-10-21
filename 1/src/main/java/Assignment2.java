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

    private final String S1 = "select * from orders where age < 20";

    private final String S2 = "select * from orders where name like '王%'";

    private final String S3 = "select count(*) from orders where sex = '男' ";

    private final String S3P = "create index sex_index on orders(sex)";

    private final String S4 = "select count(*) from orders where sex = '女' and name like '张%' and age > 50 and amount < 100;";

    private final String S4P = "create index orders_index on orders(name, amount, age, sex);";

    private final String S5 = "select count(*) from orders where name like '___' ";

    private final String S5P = "create index name_three_index on orders(name)";

    private final String S6 = "select * from products where nums>150";
    
    private final String S6P = "create index nums_index on products(nums)";

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
            statement.executeQuery(S1);
            end = System.currentTimeMillis();
            printTime(1, false);
            statement.execute("CREATE index age0 on orders(age)");
            System.out.println("Succeed to create index on age...");
            start = System.currentTimeMillis();
            statement.executeQuery(S1);
            end = System.currentTimeMillis();
            printTime(1, true);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solution2() {
        try {
            Statement statement = connection.createStatement();
            start = System.currentTimeMillis();
            statement.executeQuery(S2);
            end = System.currentTimeMillis();
            printTime(2, false);
            statement.execute("CREATE index name2 on orders(name)");
            System.out.println("Succeed to create index on age...");
            start = System.currentTimeMillis();
            statement.executeQuery(S2);
            end = System.currentTimeMillis();
            printTime(2, true);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Assignment2 assignment2 = new Assignment2();
        assignment2.init();
      //  assignment2.solution1();
         assignment2.dropIndex2();
         assignment2.solution2();
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

    private void printTime(int i, boolean isPolished) {
        String str = "";
        if(isPolished) {
            str = "+";
        }
        System.out.println("Solution" + str + i + " costs " + (end - start) + " milliseconds");
    }

    private void dropIndex(String index_name, String table_name) {
        String str = "DROP INDEX " + index_name + " ON " + table_name;
        String str1 = "SHOW INDEX FROM `orders`";
        try {
            Statement statement = connection.createStatement();
            boolean resultSet = statement.execute(str1);
            System.out.println();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void dropIndex2() {
        dropIndex("", "orders");
    }


}
