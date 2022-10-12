package org.sherlock.service.impl;

import org.sherlock.mapper.SpringBootDemoMapper;
import org.sherlock.service.SpringBootDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SpringBootDemoServiceImpl implements SpringBootDemoService {
    @Autowired
    private SpringBootDemoMapper springBootDemoMapper;

    @Override
    public String getString() {
        return springBootDemoMapper.selectAll();
    }
}
