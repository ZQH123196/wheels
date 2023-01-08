package org.example.use;


import org.example.di.annotation.Autowire;
import org.example.di.factory.BeanFactory;
import org.example.use.bean.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UseTest {

    @Resource(type = User.class)
    User userByType;

    @Resource(name = "user")
    User userByName;

    BeanFactory beanFactory = new BeanFactory();

    @BeforeEach
    public void beforeEach() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        beanFactory.init();
        beanFactory.start();
    }


    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<User> userClass = User.class;
        String beanName = "user_eor";
        System.out.println(beanFactory.getBean(userClass));
        System.out.println(beanFactory.getBean("user_eor"));
    }


    @Test
    public void t() {

    }
}
