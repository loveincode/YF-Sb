package com.loveincode.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loveincode.feign.GitHubClient;
import com.loveincode.UserService;
import com.loveincode.common.ResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyifan
 * @date :2019-05-23
 * com.loveincode.controller
 */
@Slf4j
@RestController
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private GitHubClient gitHubClient;

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @GetMapping("hello")
    public ResultDTO list() {
        String value = gitHubClient.searchRepo("21");
        log.info("123");
        for (int i = 1; i < 10000; i++) {
            log.info("" + i);
        }
        return ResultDTO.ofSuccess(GSON.toJson(value));


    }

}
