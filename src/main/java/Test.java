import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

public class Test
{
    public static void main(String[] args)
    {
        UserDao userDao = new UserDaoImpl();
        userDao.save(new User(10L));
    }
}
