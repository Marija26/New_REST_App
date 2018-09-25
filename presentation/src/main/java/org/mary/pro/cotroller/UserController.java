package org.mary.pro.cotroller;


import org.mary.pro.service.UserDAO;
import org.mary.pro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class UserController {


    public UserController() {
    }

    @Autowired
        private UserDAO dao;

        @RequestMapping("/")
        @ResponseBody
        public String welcome() {
            return "Welcome to RestTemplate Example.";
        }



        @RequestMapping(value = "/users",
                method = RequestMethod.GET,
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public List<User> getUsers() {
            List<User> list = dao.getAllUsers();
            return list;
        }


        @RequestMapping(value = "/user/{lastName}",
                method = RequestMethod.GET, //
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        @ResponseBody
        public User getUser(@PathVariable("lastName") String lastName)throws IOException

    {
            return dao.getUser(lastName);
        }


        @RequestMapping(value = "/user",
                method = RequestMethod.POST,
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public User addUser(@RequestBody User user) {

            return dao.addUser(user);

        }

        @RequestMapping(value = "/user",
                method = RequestMethod.PUT,
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public User updateUser(@RequestBody User user) {

            return dao.updateUser(user);
        }

        @RequestMapping(value = "/users/{lastName}",
                method = RequestMethod.DELETE,
                produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public void deleteUser(@PathVariable("lastName") String lastName) {
            dao.deleteUser(lastName);
        }

    }

