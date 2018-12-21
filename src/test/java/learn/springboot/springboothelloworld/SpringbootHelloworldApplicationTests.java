package learn.springboot.springboothelloworld;

import javafx.application.Application;
import learn.springboot.springboothelloworld.bean.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
/*
    @SpringBootTest 单元测试

 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootHelloworldApplicationTests {

    @Autowired
    Person person;
    @Autowired
    ApplicationContext ioc;

    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testHelloService() {
        boolean b = ioc.containsBean("helloService");
        System.out.println(b);
    }

    @Test
    public void contextLoads() {
        logger.info("this is info!");
        System.out.println(person);
    }

}
