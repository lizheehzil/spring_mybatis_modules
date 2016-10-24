package com.lizhe.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * ProjectName: spring_mybatis_modules
 * PackageName: com.lizhe.test
 * Created by lizhe on 2016/10/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class Test {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private Environment environment;

    @org.junit.Test
    public void test() throws IOException {
        URL resource = resourceLoader.getClassLoader().getResource("test.txt");

    }

}
