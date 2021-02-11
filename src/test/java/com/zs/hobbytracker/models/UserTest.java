package com.zs.hobbytracker.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    User user;

    @Before
    public void setup() {
        user = new User();
    }

    @Test
    public void fieldIdTest() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    @Test
    public void fieldNameTest() {
        user.setName("nameOfUser");
        assertEquals("nameOfUser", user.getName());
    }


}
