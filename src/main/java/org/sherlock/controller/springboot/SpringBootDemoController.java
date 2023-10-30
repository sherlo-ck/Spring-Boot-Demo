package org.sherlock.controller.springboot;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.sherlock.model.User;
import org.sherlock.service.SpringBootDemoService;
import org.sherlock.utils.ReturnMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("查询")
    @GetMapping("/test1")
    public void testSpringBootOne() {
         springBootDemoService.test();
    }

    @ApiOperation("获取静态变量注入值")
    @GetMapping("/get")
    public String getTestValue() {
        System.out.println(testValue);
        return testValue;
    }


}
