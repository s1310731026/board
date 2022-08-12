package com.jasoncode.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class BoardControllerTest {

    @Autowired
    private BoardController controller;

    @Test
    void getAll() {
        Assert.assertFalse(controller.getAll().isEmpty());
    }

    @Test
    void create() {
        Assert.assertNotNull(controller.create("test1","title1ssssss","jason","",""));

    }

    @Test
    void findById() {
        Assert.assertNotEquals(null,controller.findById(1));
    }

    @Test
    void update() {
    }
}