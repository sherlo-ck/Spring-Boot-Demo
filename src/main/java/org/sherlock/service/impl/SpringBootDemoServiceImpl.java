package org.sherlock.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NoHttpResponseException;
import org.sherlock.mapper.SpringBootDemoMapper;
import org.sherlock.model.TestModel;
import org.sherlock.model.User;
import org.sherlock.service.RetryService;
import org.sherlock.service.SpringBootDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class SpringBootDemoServiceImpl implements SpringBootDemoService {
    @Autowired
    private SpringBootDemoMapper springBootDemoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RetryService retryService;

    @Override
    public List<User> getString() {
        return springBootDemoMapper.selectAll();
    }

    @Override
    public List<User> getOne(Integer id) {
        try {
            retryService.callPost();
        } catch (NoHttpResponseException e) {
            log.info("重试未生效");
        }
        return springBootDemoMapper.selectOne(id);
    }

    @Override
    public void test() {
        List<User> userList = springBootDemoMapper.query();
        Map<Integer, List<User>> collect = userList.stream().collect(Collectors.groupingBy(User::getSex));
        TestModel testModel = new TestModel();
        testModel.setSex0(collect.get(0));
        testModel.setSex1(collect.get(1));
        springBootDemoMapper.update(collect.get(0), collect.get(1));
    }

}
