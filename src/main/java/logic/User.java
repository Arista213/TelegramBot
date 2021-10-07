package logic;

public class User {
    private boolean m_isAdmin;

    public User() {

    }

    public void switchAdminStatus() {
        m_isAdmin = !m_isAdmin;
    }

    public boolean isAdmin() {
        return m_isAdmin;
    }
}
