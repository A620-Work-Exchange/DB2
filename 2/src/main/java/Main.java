import dao.BillDAO;
import dao.UserDAO;

public class Main {
    private static UserDAO userDAO = new UserDAO();
    public static void main(String[] args) {
        userDAO.addUser("py", "123456");
    }

}
