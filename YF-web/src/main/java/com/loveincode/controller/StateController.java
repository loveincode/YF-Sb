package com.loveincode.controller;

import com.loveincode.enums.EventsEnums;
import com.loveincode.enums.StatusEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
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

    @RequestMapping("/testMachine")
    @ResponseBody
    public void testMachine() {
        stateMachine.start();
        //statemachine.sendEvent(EventsEnums.PAY);
        //statemachine.sendEvent(EventsEnums.RECEIVE);
    }

    @RequestMapping("/testMachine2")
    @ResponseBody
    public void testMachine2() {
        //statemachine.start();
        stateMachine.sendEvent(EventsEnums.PAY);
        //statemachine.sendEvent(EventsEnums.RECEIVE);
    }

}
