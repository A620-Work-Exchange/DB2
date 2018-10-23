import dao.BillDAO;
import dao.UserDAO;
import domain.User;

public class Main {
    private static UserDAO userDAO = new UserDAO();
    public static void main(String[] args) {

        userDAO.addUser("6+", "123456");

    }

}
