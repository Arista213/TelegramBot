import api.UserApi;
import model.Mode;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Тесты для класса UserApi, отвественного за работу с пользователями.
 */
public class UserApiTests {
    User user = new User(0L);

    @BeforeEach
    void initiateModeApi() {
        UserApi.dropUsers();
    }

    @Test
    void addModeTest() {
        UserApi.add(user, Mode.User);
        assert (UserApi.getUsersAmount() == 1);
    }

    @Test
    void isUserModeTest() {
        UserApi.add(user, Mode.User);
        assert (!UserApi.isAdmin(user));
    }

    @Test
    void isAdminModeTest() {
        UserApi.add(user, Mode.Admin);
        assert (UserApi.isAdmin(user));
    }

    @Test
    void updateModeTest() {
        UserApi.add(user, Mode.User);
        assert (!UserApi.isAdmin(user));

        UserApi.update(user, Mode.Admin);
        assert (UserApi.isAdmin(user));
    }
}
