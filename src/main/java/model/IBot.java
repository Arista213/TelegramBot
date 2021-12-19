package model;

import dao.DishDao;
import dao.UserDao;
import service.DishService;
import service.UserService;

/**
 * Функционал бота.
 */
public interface IBot
{
    void handleMessage(User user, Message message);

    DishDao getDishDao();

    UserService getUserService();

    DishService getDishService();

    UserDao getUserDao();
}
