package org.mary.pro.service;


import org.mary.pro.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;
@Component
@Repository
public class UserDAO {



    public UserDAO() {

    }

    private static final Map<String, User> UMap = new HashMap<String, User>();

    static {
        initUsers();
    }

    private static void initUsers() {
        User user1 = new User("Ivan", "Ivanov", "manager");
        User user2 = new User("Oleg", "Petrov", "lawyer");
        User user3 = new User("Nick", "Cage", "doctor");

        UMap.put(user1.getLastName(), user1);
        UMap.put(user2.getLastName(), user2);
        UMap.put(user3.getLastName(), user3);
    }

    public User getUser(String lastName) {
        return UMap.get(lastName);
    }

    public User addUser(User user) {
        UMap.put(user.getLastName(), user);
        return user;
    }

    public User updateUser(User user) {
        UMap.put(user.getLastName(), user);
        return user;
    }

    public void deleteUser(String lastName) {
        UMap.remove(lastName);
    }

    public List<User> getAllUsers() {
        Collection<User> c = UMap.values();
        List<User> list = new ArrayList<User>();
        list.addAll(c);
        return list;
    }


}
