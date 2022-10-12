package org.sherlock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.sherlock.service.SpringBootDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springBoot")
@Api(value = "springDemo", tags = {"springDemo"})
public class SpringBootDemoController {
    @Autowired
    private SpringBootDemoService springBootDemoService;

    @ApiOperation("查询")
    @GetMapping("/test")
    public String testSpringBoot() {
        String abc = "Hello SpringBoot";
        return abc;
    }
}
