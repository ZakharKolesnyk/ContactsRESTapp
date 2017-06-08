package com.gmail.kolesnyk.zakhar.controller;

import com.gmail.kolesnyk.zakhar.configuration.WebConfig;
import com.gmail.kolesnyk.zakhar.user.User;
import com.gmail.kolesnyk.zakhar.user.UserDao;
import com.google.gson.Gson;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class UserControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private User user;

    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
        user = new User("TEST NAME", "TEST PHONE");
        userDao.save(user);
    }

    @After
    public void clean() {
        userDao.remove(user);
    }

    @Configuration
    @EnableWebMvc
    public class TestConfiguration {
        @Bean
        public UserController userController() {
            return userController;
        }
    }

    @Test
    public void createUser() throws Exception {
        User user = new User("CREATE TEST NAME", "CREATE TEST PHONE");
        String json = new Gson().toJson(user);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json));
        MvcResult mvcResult = resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("userName").value(user.getUserName()))
                .andExpect(jsonPath("phone").value(user.getPhone())).andReturn();
        user = new Gson().fromJson(mvcResult.getResponse().getContentAsString(), User.class);
        userDao.remove(user);
    }

    @Test
    public void createUserInvalidWithNotUniqueName() throws Exception {
        User user = new User("CREATE TEST NAME", "CREATE TEST PHONE");
        userDao.save(user);
        user.setPhone("CREATE TEST PHONE2");
        String json = new Gson().toJson(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json)).andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("Such name of user already exist in system"))
                .andReturn();
        userDao.remove(user);
    }

    @Test
    public void createUserInvalidWithNotUniquePhone() throws Exception {
        User user = new User("CREATE TEST NAME", "CREATE TEST PHONE");
        userDao.save(user);
        user.setUserName("CREATE TEST NAME2");
        String json = new Gson().toJson(user);
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json)).andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("Such phone of user already exist in system"))
                .andReturn();
        userDao.remove(user);
    }

    @Test
    public void getUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/id/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("userName").value(user.getUserName()))
                .andExpect(jsonPath("phone").value(user.getPhone()));
    }

    @Test
    public void getUserByIdInvalid() throws Exception {
        userDao.remove(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/id/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("User not found"));
        userDao.save(user);
    }

    @Test
    public void getUserByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/name/{name}", user.getUserName())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("userName").value(user.getUserName()))
                .andExpect(jsonPath("phone").value(user.getPhone()));
    }

    @Test
    public void getUserByNameInvalid() throws Exception {
        userDao.remove(user);
        mockMvc.perform(MockMvcRequestBuilders.get("/name/{name}", user.getUserName())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("User not found"));
        userDao.save(user);
    }

    @Test
    public void updateUser() throws Exception {
        user.setPhone("UPDATE TEST PHONE");
        user.setUserName("UPDATE TEST NAME");
        String json = new Gson().toJson(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/update/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("id").value(user.getId()))
                .andExpect(jsonPath("userName").value(user.getUserName()))
                .andExpect(jsonPath("phone").value(user.getPhone()));
    }

    @Test
    public void updateUserInvalid() throws Exception {
        user.setPhone("UPDATE TEST PHONE");
        user.setUserName("UPDATE TEST NAME");
        userDao.remove(user);
        String json = new Gson().toJson(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/update/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("User with such ID not found"));
        userDao.save(user);
    }

    @Test
    public void deleteUser() throws Exception {
        String json = new Gson().toJson(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/del")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("deleting successful"));
        userDao.save(user);
    }

    @Test
    public void deleteUserInvalid() throws Exception {
        String json = new Gson().toJson(user);
        userDao.remove(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/del")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("Deleting not happens"));
        userDao.save(user);
    }

    @Test
    public void deleteUserById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/del/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("deleting successful"));
        userDao.save(user);
    }

    @Test
    public void deleteUserByIdInvalid() throws Exception {
        userDao.remove(user);
        mockMvc.perform(MockMvcRequestBuilders.put("/del/{id}", user.getId())
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(400))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("status").value("Deleting not happens"));
        userDao.save(user);
    }
}