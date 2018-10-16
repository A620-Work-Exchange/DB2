import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Assignment1 {
    Connection connection;
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/ERP?useUnicode=" +
            "true&characterEncoding=utf-8&useSSL=false";
    private final static String USER = "root";
    private final static String PW = "1212hjkl";

    private static final String CREATE_PRODUCTION =
            "CREATE TABLE IF NOT EXISTS  `Production`\n" +
            "(\n" +
            "`pid`   VARCHAR(60),\n" +
            "`pname` VARCHAR(60),\n" +
            "`pdate` DATE\n" +
            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private static final String CREATE_AGENT = "CREATE TABLE IF NOT EXISTS `Agent`\n" +
            "(\n" +
            "  `aid`   VARCHAR(60),\n" +
            "  `aname` VARCHAR(80)\n" +
            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private static final String CREATE_SALES = "CREATE TABLE IF NOT EXISTS `Sales`\n" +
            "(\n" +
            "  `sid`   VARCHAR(60),\n" +
            "  `sname` VARCHAR(60),\n" +
            "  `aid`   VARCHAR(60)\n" +
            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private static final String CREATE_DEAL = "CREATE TABLE IF NOT EXISTS `Deal`\n" +
            "(\n" +
            "  `sid`    VARCHAR(60),\n" +
            "  `pid`    VARCHAR(60),\n" +
            "  `volume` VARCHAR(60)\n" +
            ")ENGINE=InnoDB DEFAULT CHARSET=utf8;";

    private static String LOAD_ORDERS_DATA =
            "load data local infile \"/Users/py/Downloads/索引作业数据/data1.txt\"\n" +
            "into table orders(id, name, age, sex, amount);";

    private static String LOAD_PRODUCTS_DATA =
            "load data local infile \"/Users/py/Downloads/索引作业数据/data2.txt\"\n" +
            "into table products(id, pid, nums);";

    private static String DELETE_ORDERS = "delete from orders";

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

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            if(statement.executeLargeUpdate(CREATE_PRODUCTION) == 0 && statement.executeLargeUpdate(CREATE_AGENT) == 0
                    && statement.executeLargeUpdate(CREATE_SALES) == 0 &&
                    statement.executeLargeUpdate(CREATE_DEAL) == 0){
                System.out.println("Succeed to create all...");
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private void loadOrdersData() {
        try {
            Statement statement = connection.createStatement();
            long startTime=System.currentTimeMillis();
            statement.execute(LOAD_ORDERS_DATA);
            long endTime=System.currentTimeMillis();
            System.out.println("Load orders data costs: " + (endTime - startTime)/1000 + " s.");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadProductsData() {
        try {
            Statement statement = connection.createStatement();
            long startTime=System.currentTimeMillis();
            statement.execute(LOAD_PRODUCTS_DATA);
            long endTime=System.currentTimeMillis();
            System.out.println("Load products data costs: " + (endTime - startTime)/1000 + " s.");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solution1() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT a.pid FROM " +
                    "(SELECT sum(volume), pid FROM Deal GROUP BY pid ORDER " +
                    "BY sum(volume) DESC )a limit 2");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "pid: " + resultSet.getString(1) + " " +
                        "sum: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Assignment1 assignment1 = new Assignment1();
        assignment1.init();
//        assignment1.loadOrdersData();
//        assignment1.loadProductsData();
        assignment1.solution1();

    }

    private void printOneColumn(ResultSet resultSet, String title){
        try {
            if(!resultSet.next())
                System.out.println("查询结果为空...");
            else {
                while (resultSet.next()) {
                    System.out.println(title + " " + resultSet.getString(1));
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private ResultSet execute(String sql){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
