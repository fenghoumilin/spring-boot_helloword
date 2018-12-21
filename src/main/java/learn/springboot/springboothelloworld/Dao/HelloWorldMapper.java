package learn.springboot.springboothelloworld.Dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface HelloWorldMapper {

    int getUserIdByName(String name);
}
