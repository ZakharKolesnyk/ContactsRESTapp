package com.gmail.kolesnyk.zakhar.controller;

import com.gmail.kolesnyk.zakhar.service.userService.UserService;
import com.gmail.kolesnyk.zakhar.user.User;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public String createUser(@RequestBody User user) throws IOException {
        return new Gson().toJson(userService.create(user));
    }

    @ResponseBody
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getUserById(@PathVariable Integer id) throws IOException {
        return new Gson().toJson(userService.getById(id));
    }

    @ResponseBody
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = "application/json")
    public String getUserByName(@PathVariable String name) throws IOException {
        return new Gson().toJson(userService.getByName(name));
    }

    @ResponseBody
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public String updateUser(@PathVariable Integer id, @RequestBody User user) throws IOException {
        user.setId(id);
        return new Gson().toJson(userService.update(user));
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public String deleteUser(@RequestBody User user) {
        userService.delete(user);
        return "{\"status\": \"deleting successful\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/del/{id}", method = RequestMethod.PUT, produces = "application/json")
    public String deleteUser(@PathVariable Integer id) {
        userService.delete(id);
        return "{\"status\": \"deleting successful\"}";
    }
}
