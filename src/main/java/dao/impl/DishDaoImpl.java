package dao.impl;

import dao.DishDao;
import models.Dish;
import models.JSONDish;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация DishDao через Hibernate.
 */
public class DishDaoImpl extends DishDao {
    @Override
    public Dish get(String title) {
        JSONDish jsonDish = HibernateSessionFactoryUtil.getSessionFactory().openSession().get(JSONDish.class, title);
        return jsonDish.getDish();
    }

    @Override
    public List<Dish> getAll() {
        List<JSONDish> jsonDishes = (List<JSONDish>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From JSONDish").list();
        return jsonDishes.stream().map(JSONDish::getDish).collect(Collectors.toList());
    }

    @Override
    public void save(Dish dish) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        if (HibernateSessionFactoryUtil.getSessionFactory().openSession().get(JSONDish.class, dish.getTitle()) != null)
            return;
        Transaction tx1 = session.beginTransaction();
        JSONDish jsonDish = new JSONDish(dish);
        session.save(jsonDish);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(String title, Dish dish) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        JSONDish jsonDish = new JSONDish(dish);
        session.update(jsonDish);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Dish dish) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        JSONDish jsonDish = new JSONDish(dish);
        session.delete(jsonDish);
        tx1.commit();
        session.close();
    }
}
