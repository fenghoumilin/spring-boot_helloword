package learn.springboot.springboothelloworld.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
    将配置文件中配置的属性值映射到数组中
    @ConfigurationProperties 告诉Springboot将本类中到所有值和相关配置文件进行绑定
    //默认从全局文件中获取值
    prefix = "persion" 指定配置文件的配置

    @PropertySource(value = {classpath:xxx})
    加载指定文件


 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    String name;
    int age;
    Map<String, Object> maps;
    List<String> lists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map getMaps() {
        return maps;
    }

    public void setMaps(Map maps) {
        this.maps = maps;
    }

    public List getLists() {
        return lists;
    }

    public void setLists(List lists) {
        this.lists = lists;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", maps=" + maps +
                ", lists=" + lists +
                '}';
    }
}
