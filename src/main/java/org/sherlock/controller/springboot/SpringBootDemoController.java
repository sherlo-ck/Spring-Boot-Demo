package org.sherlock.controller.springboot;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.sherlock.model.User;
import org.sherlock.service.PostRestTemplateService;
import org.sherlock.service.SpringBootDemoService;
import org.sherlock.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/springBoot")
@Slf4j
@Api(value = "springDemo", tags = {"springDemo"})
public class SpringBootDemoController {
    @Autowired
    private SpringBootDemoService springBootDemoService;

    @Value("${test.testValue}")
    private String value;

    @Autowired
    private PostRestTemplateService postRestTemplateService;

    private static String testValue;

    @PostConstruct
    private void init() {
        testValue = this.value;
    }

    @ApiOperation("查询")
    @GetMapping("/test")
    public ReturnMsg<List<User>> testSpringBoot() {
        return new ReturnMsg<>(springBootDemoService.getString());
    }

    @ApiOperation("查询")
    @GetMapping("/testOne")
    public List<User> testSpringBootOne(@RequestParam("id") Integer id) {
        return springBootDemoService.getOne(id);
    }

    @ApiOperation("获取门禁请求参数")
    @GetMapping("/restTemplate")
    public JSONObject PostRestTemplate() {
        try {
             return postRestTemplateService.postUrl();
        } catch (JSONException e) {
            log.error("json转化异常{}", e);
            throw new RuntimeException(e);
        }
    }
    @ApiOperation("获取静态变量注入值")
    @GetMapping("/get")
    public String getTestValue() {
        System.out.println(testValue);
        return testValue;
    }

    @ApiOperation("序列化与反序列化测试")
    @GetMapping("/Serializable")
    public void serializableTest(User user) {
        springBootDemoService.testSerializable(user);
    }


}
