package com.zs.hobbytracker.service;

import com.zs.hobbytracker.dao.UserDao;
import com.zs.hobbytracker.exception.ApplicationRuntimeException;
import com.zs.hobbytracker.models.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    UserService userService;

    @Mock
    UserDao userDao;

    @Mock
    Connection connection;

    @Mock
    Logger logger;

    User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService();
        user = new User();
        user.setName("testAdd");
        user.setId(1);
    }

    @Test
    public void addUserTest() throws ApplicationRuntimeException {

        userService = new UserService(userDao, logger);
        when(userDao.addUser(connection, user)).thenReturn(1);
        userService.addUser(connection, user);
        verify(logger).info(anyString());
    }

}
