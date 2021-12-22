package models;

import dao.DishDao;
import dao.UserDao;
import services.DishService;
import services.UserService;

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
