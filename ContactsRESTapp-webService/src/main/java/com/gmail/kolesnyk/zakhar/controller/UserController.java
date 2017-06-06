package com.gmail.kolesnyk.zakhar.controller;

import com.gmail.kolesnyk.zakhar.service.userService.UserService;
import com.gmail.kolesnyk.zakhar.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @ResponseBody
    @RequestMapping(value = "/id/{idUser}", method = RequestMethod.GET)
    public User getUserById(@PathVariable Integer idUser) {
        return userService.getById(idUser);
    }

    @ResponseBody
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public User getUserByName(@PathVariable String name) {
        return userService.getByName(name);
    }

    @ResponseBody
    @RequestMapping(value = "/upd/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.PUT)
    public String deleteUser(@RequestBody User user) {
        if (userService.delete(user)) {
            return "{\"status\": \"deleting successful\"}";
        } else {
            return "{\"status\": \"deleting not happened\"}";
        }
    }
}
