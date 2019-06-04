package com.loveincode.controller;

import com.loveincode.enums.EventsEnums;
import com.loveincode.enums.StatusEnums;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huyifan
 * @date :2019-05-28
 * com.loveincode.controller
 */

@RestController
public class StateController {

    @Autowired
    private StateMachine<StatusEnums, EventsEnums> stateMachine;

    @GetMapping("/testMachine")
    @ApiOperation("状态机测试")
    public void testMachine() {
        stateMachine.start();
        //statemachine.sendEvent(EventsEnums.PAY);
        //statemachine.sendEvent(EventsEnums.RECEIVE);
    }

    @GetMapping("/testMachine2")
    public void testMachine2() {
        //statemachine.start();
        stateMachine.sendEvent(EventsEnums.PAY);
        //statemachine.sendEvent(EventsEnums.RECEIVE);
    }

}
