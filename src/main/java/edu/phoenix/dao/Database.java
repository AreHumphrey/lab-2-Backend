package edu.phoenix.dao;

import edu.phoenix.model.User;

import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<User> dataBase = new ArrayList<>();


    public static void addUser(String name, String login, String password) {
        try {
            if (getUser(login, password) == null) {
                dataBase.add(new User(name, login, password));
                System.out.println("Пользователь успешно добавлен");
            } else {
                throw new UserAlreadyExists();
            }
        } catch (UserAlreadyExists e) {
            e.errorMessage();
        }
    }

    public static User getUser(String login, String password) {
        for (User user : dataBase) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static void deleteUser(String login) {
        for (User user : dataBase) {
            if (user.getLogin().equals(login)) {
                dataBase.remove(user);
                return;
            }
        }
        System.out.println("Пользователь не найден");
    }

    public static void updateUser(User user) {
        for (User existingUser : dataBase) {
            if (existingUser.getLogin().equals(user.getLogin())) {
                existingUser.setName(user.getName());
                existingUser.setPassword(user.getPassword());
            }
        }
        System.out.println("Пользователь не найден");
    }

    static class UserAlreadyExists extends Exception {
        public void errorMessage() {
            System.out.println("Пользователь с такими данными уже сужествует");
        }
    }

    ;

}
