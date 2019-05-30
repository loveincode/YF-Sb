package com.loveincode.config.statemachine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @author huyifan
 * @date :2019-05-28
 * com.loveincode.config
 */
@Slf4j
@WithStateMachine
public class StateEventConfig {

    @OnTransition(target = "UNPAID")
    public void create() {
        log.info("-------订单创建，待支付");
    }

    @OnTransition(source = "UNPAID", target = "WAITING_FOR_RECEIVE")
    public void pay() {
        log.info("---------用户完成支付，待收货");
    }

    @OnTransition(source = "WAITING_FOR_RECEIVE", target = "DONE")
    public void receive() {
        log.info("---------用户已收货，订单完成");
    }
}