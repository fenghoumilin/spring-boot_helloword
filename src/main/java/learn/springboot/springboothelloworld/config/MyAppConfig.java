package learn.springboot.springboothelloworld.config;

import learn.springboot.springboothelloworld.Service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  @Configuration 指明当前类是一个配置类
 */
@Configuration
public class MyAppConfig {

    /*@Bean
    public HelloService helloService(){
        return new HelloService();
    }*/
}
