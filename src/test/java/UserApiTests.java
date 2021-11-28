import api.UserApi;
import model.Mode;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса UserApi, отвественного за работу с пользователями.
 */
public class UserApiTests {


    @BeforeEach
    void initiateModeApi() {
        UserApi.dropUsers();
    }

    /**
     * Тест проверяет количество пользователей после добавления
     */
    @Test
    void addModeTest() {
        User user = new User(0L);
        UserApi.add(user, Mode.User);
        assert (UserApi.getUsersAmount() == 1);
    }

    /**
     * Проверяет режим пользователя.
     */
    @Test
    void isUserModeTest() {
        User user = new User(0L);
        UserApi.add(user, Mode.User);
        assert (!UserApi.isAdmin(user));
    }

    /**
     * Проверяет режим пользователя.
     */
    @Test
    void isAdminModeTest() {
        User user = new User(0L);
        UserApi.add(user, Mode.Admin);
        assert (UserApi.isAdmin(user));
    }

    /**
     * Проверяет режим пользователя после его смены.
     */
    @Test
    void updateModeTest() {
        User user = new User(0L);
        UserApi.add(user, Mode.User);
        assert (!UserApi.isAdmin(user));

        UserApi.update(user, Mode.Admin);
        assert (UserApi.isAdmin(user));
    }
}
