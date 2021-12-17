package dao.impl;

import dao.UserDao;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleUserDao extends UserDao
{
    private final Map<Long, User> users = new HashMap<>();

    @Override
    public User get(long id)
    {
        return users.get(id);
    }

    @Override
    public List<User> getAll()
    {
        return new ArrayList<>(users.values());
    }

    @Override
    public void save(User user)
    {
        users.put(user.getId(), user);
    }

    @Override
    public void update(long id, User user)
    {
        users.put(id, user);
    }

    @Override
    public void delete(User user)
    {
        users.remove(user.getId());
    }
}
