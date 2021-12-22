package dao.impl;

import dao.UserDao;
import models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;

/**
 * Реализация UserDao через Hibernate.
 */
public class UserDaoImpl extends UserDao
{
    @Override
    public User get(long id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    @Override
    public List<User> getAll()
    {
        return (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From User").list();
    }

    @Override
    public void save(User user)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        if (HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, user.getId()) != null) return;
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(User user)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User user)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }
}
