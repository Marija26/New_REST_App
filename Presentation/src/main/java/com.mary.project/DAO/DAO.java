package com.mary.project.DAO;

import com.mary.project.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class DAO {
    private static final Map<String, User> empMap = new HashMap<String, User>();

    static {
        initEmps();
    }

    private static void initEmps() {
        User user1 = new User("Ivan", "Ivanov", "manager");
        User user2 = new User("Oleg", "Petrov", "lawyer");
        User user3 = new User("Nick", "Cage", "doctor");

        empMap.put(user1.getLastName(), user1);
        empMap.put(user2.getLastName(), user2);
        empMap.put(user3.getLastName(), user3);
    }

    public User getUser(String lastName) {
        return empMap.get(lastName);
    }

    public User addUser(User user) {
        empMap.put(user.getLastName(), user);
        return user;
    }

    public User updateUser(User user) {
        empMap.put(user.getLastName(), user);
        return user;
    }

    public void deleteUser(String lastName) {
        empMap.remove(lastName);
    }

    public List<User> getAllUsers() {
        Collection<User> c = empMap.values();
        List<User> list = new ArrayList<User>();
        list.addAll(c);
        return list;
    }
}
