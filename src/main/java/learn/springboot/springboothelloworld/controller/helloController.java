package learn.springboot.springboothelloworld.controller;

import learn.springboot.springboothelloworld.Dao.HelloWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//类的所有方法都加上@ResponseBody注解

@Controller
public class helloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello world!";
    }

    @Autowired
    HelloWorldMapper helloWorldMapper;
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        int ans = helloWorldMapper.getUserIdByName("cmd");
        logger.info("ans = " + ans);
        map.put("hello", ans);
        return "success";
    }

}
