package org.sherlock.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.sherlock.mapper.SpringBootDemoMapper;
import org.sherlock.model.User;
import org.sherlock.model.UserTest;
import org.sherlock.service.SpringBootDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SpringBootDemoServiceImpl implements SpringBootDemoService {
    @Autowired
    private SpringBootDemoMapper springBootDemoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<User> getString() {
        List<User> users = springBootDemoMapper.selectAll();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId().equals(1)) {
                iterator.remove();
            }
        }
        return users;
    }

    @Override
    public List<User> getOne(Integer id) {

        return springBootDemoMapper.selectOne(id);
    }

    @Override
    public void testSerializable(User user) {
        List<User> users = springBootDemoMapper.selectAll();
        redisTemplate.opsForValue().set("test", users);
        List<UserTest> test = (List<UserTest>) redisTemplate.opsForValue().get("test");
        System.out.println(JSONObject.toJSONString(test));
    }

}
