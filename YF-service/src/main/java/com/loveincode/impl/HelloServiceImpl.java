package com.loveincode.impl;

import com.loveincode.HelloService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huyifan
 * @date :2019-05-23
 * com.loveincode
 */
@Service
public class HelloServiceImpl implements HelloService {


    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String hello() {
        redisTemplate.opsForValue().set("yf", "yf");
        String value = redisTemplate.opsForValue().get("yf");
        return value;
    }
}
