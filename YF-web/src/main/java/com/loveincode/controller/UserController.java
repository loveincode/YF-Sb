package com.loveincode.controller;

import com.loveincode.UserService;
import com.loveincode.common.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyifan
 * @date :2019-05-23
 * com.loveincode.controller
 */
@RestController
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("hello")
    public ResultDTO list() {
        return ResultDTO.ofSuccess("hello");
    }

}
