package org.sherlock.service;

import com.alibaba.fastjson.JSONObject;
import org.sherlock.model.User;

import java.util.List;

public interface SpringBootDemoService {

    List<User> getString();

    List<User> getOne(Integer id);

    void test();
}
