package com.mary.project.cotroller;


import com.mary.project.DAO.DAO;
import com.mary.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class Сontroller {

        @Autowired
        private DAO dao;

        @RequestMapping("/")
        @ResponseBody
        public String welcome() {
            return "Welcome to RestTemplate Example.";
        }

        // URL:
        // http://localhost:8080/SpringMVCRESTful/employees
        // http://localhost:8080/SpringMVCRESTful/employees.xml
        // http://localhost:8080/SpringMVCRESTful/employees.json
        @RequestMapping(value = "/users", //
                method = RequestMethod.GET, //
                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public List<User> getUsers() {
            List<User> list = dao.getAllUsers();
            return list;
        }

        // URL:
        // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
        // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.xml
        // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.json
        @RequestMapping(value = "/user/{lastName}", //
                method = RequestMethod.GET, //
                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public User getUser(@PathVariable("lastName") String lastName) {
            return dao.getUser(lastName);
        }

        // URL:
        // http://localhost:8080/SpringMVCRESTful/employee
        // http://localhost:8080/SpringMVCRESTful/employee.xml
        // http://localhost:8080/SpringMVCRESTful/employee.json
        @RequestMapping(value = "/user", //
                method = RequestMethod.POST, //
                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public User addUser(@RequestBody User user) {

            return dao.addUser(user);

        }

        // URL:
        // http://localhost:8080/SpringMVCRESTful/employee
        // http://localhost:8080/SpringMVCRESTful/employee.xml
        // http://localhost:8080/SpringMVCRESTful/employee.json
        @RequestMapping(value = "/user", //
                method = RequestMethod.PUT, //
                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public User updateUser(@RequestBody User user) {

            return dao.updateUser(user);
        }

        // URL:
        // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
        @RequestMapping(value = "/users/{lastName}", //
                method = RequestMethod.DELETE, //
                produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
        @ResponseBody
        public void deleteUser(@PathVariable("lastName") String lastName) {
            dao.deleteUser(lastName);
        }

    }

