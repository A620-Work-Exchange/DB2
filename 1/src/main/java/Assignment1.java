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
            ResultSet resultSet = statement.executeQuery("select pname, volume from Production, Deal\n" +
                    "where Production.pid = Deal.pid \n" +
                    "order by volume desc\n" +
                    "limit 2");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "pname: " + resultSet.getString(1) + " " +
                        "volume: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solution2() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select pname, volume from Production p, Deal d \n" +
                    "where p.pid = d.pid and p.pdate >= str_to_date('2017-1-1', 'yyyy-mm-dd');");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "pname: " + resultSet.getString(1) + " " +
                        "volume: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void solution3() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select sname, aname from Sales s, Agent a\n" +
                    "where s.aid = a.aid and s.sid in (\n" +
                    "\tselect sid from Deal d where d.pid = 2\n" +
                    "    and d.volume > 100\n" +
                    ");");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "sname: " + resultSet.getString(1) + " " +
                        "aname: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void solution4() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select aname, pname, volume \n" +
                    "from Agent a, Production p, Deal d, Sales s\n" +
                    "where d.pid =  p.pid and d.sid = s.sid and a.aid = s.aid\n" +
                    "");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "sname: " + resultSet.getString(1) + " " +
                        "aname: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void solution5() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select pname, count(*) from Production p, Deal d\n" +
                    "where p.pid = d.pid and p.pid in (select pid, count(*) from \n" +
                    "\tDeal group by pid\n" +
                    ");");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "sname: " + resultSet.getString(1) + " " +
                        "aname: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void solution6() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select aname, sname from Agent a, Sales s \n" +
                    "where a.aid = s.aid and a.aid = (\n" +
                    "\tselect aid from Agent where aname like '%BBB%'\n" +
                    ");");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "sname: " + resultSet.getString(1) + " " +
                        "aname: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void solution7() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select pname, volume from Production, Deal\n" +
                    "where Production.pid = Deal.pid \n" +
                    "order by volume asc\n" +
                    "limit 1" +
                    ";");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "sname: " + resultSet.getString(1) + " " +
                        "aname: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void solution8() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select pname, sname, volume\n" +
                    "  from Production, Sales, Deal\n" +
                    " where Sales.sid in\n" +
                    "       (select sid\n" +
                    "          from Deal t1, (select pid, max(volume)a from Deal group by pid) t2\n" +
                    "         where t1.pid = t2.pid\n" +
                    "           and t1.volume = t2.a)\n" +
                    "           and Production.pid = Deal.pid and Deal.sid = Sales.sid;");
            if(!resultSet.next()) {
                System.out.println("查询结果为空");
            }else {
                printOneColumn(resultSet, "sname: " + resultSet.getString(1) + " " +
                        "aname: " + resultSet.getString(2));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Assignment1 assignment1 = new Assignment1();
        assignment1.init();
      //  assignment1.loadOrdersData();
          assignment1.loadProductsData();
//        assignment1.solution1();
//        assignment1.solution2();
//        assignment1.solution3();
//        assignment1.solution6();
        assignment1.solution8();

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
