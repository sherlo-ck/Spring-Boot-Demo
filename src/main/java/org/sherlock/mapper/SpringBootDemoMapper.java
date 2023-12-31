package org.sherlock.mapper;

import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.sherlock.model.TestModel;
import org.sherlock.model.User;

import java.util.List;

public interface SpringBootDemoMapper {
    // 查询所以
    List<User> selectAll();
    // 查询一条
    List<User> selectOne(@Param("id")Integer id);

    List<User> query();

    void update(@Param("list1") List<User> users, @Param("list2") List<User> users1);
}
