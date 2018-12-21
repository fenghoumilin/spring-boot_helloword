package learn.springboot.springboothelloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Locale;

/*
    声明该类为springboot应用
    是一个集合注解
    @SpringBootApplication{

        @EnableAutoConfiguration 扫描该类所在包下所有包与类的controller，导入组件，并配置好

    }


*/
/*
    @ImportResource(locations = {"classpath:beans.xml"})
    @ImportResource 导入Spring配置文件，让配置文件里面的内容生效，可以加载多个自己创建的spring配置文件
 */

/**
 *  @EnableWebMvc 全面接管springMVC配置，web自动配置全部失效
 */

@SpringBootApplication
public class SpringbootHelloworldApplication {

    public static void main(String[] args) {
        //springboot 启动装置
        SpringApplication.run(SpringbootHelloworldApplication.class, args);
    }



}
