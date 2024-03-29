package com.loveincode.config.statemachine;

import com.loveincode.enums.EventsEnums;
import com.loveincode.enums.StatusEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.EnumSet;

/**
 * @author huyifan
 * @date :2019-05-28
 * com.loveincode.config
 */
@Slf4j
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<StatusEnums, EventsEnums> {

    /**
     * 用来初始化当前状态机拥有哪些状态，其中initial(States.UNPAID)定义了初始状态为UNPAID，
     * states(EnumSet.allOf(States.class))则指定了使用上一步中定义的所有状态作为该状态机的状态定义。
     *
     * @param states s
     * @throws Exception e
     */
    @Override
    public void configure(StateMachineStateConfigurer<StatusEnums, EventsEnums> states)
        throws Exception {
        states
            .withStates()
            .initial(StatusEnums.UNPAID)
            .states(EnumSet.allOf(StatusEnums.class));
    }

    /**
     * 用来初始化当前状态机有哪些状态迁移动作，其中命名中我们很容易理解每一个迁移动作，都有来源状态source，目标状态target以及触发事件event。
     *
     * @param transitions t
     * @throws Exception e
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<StatusEnums, EventsEnums> transitions)
        throws Exception {
        transitions
            .withExternal()
            .source(StatusEnums.UNPAID).target(StatusEnums.WAITING_FOR_RECEIVE)
            .event(EventsEnums.PAY)
            .and()
            .withExternal()
            .source(StatusEnums.WAITING_FOR_RECEIVE).target(StatusEnums.DONE)
            .event(EventsEnums.RECEIVE);
    }

    /**
     * 为当前的状态机指定了状态监听器，其中listener()则是调用了下一个内容创建的监听器实例，用来处理各个各个发生的状态迁移事件
     *
     * @param config config
     * @throws Exception e
     */
    @Override
    public void configure(StateMachineConfigurationConfigurer<StatusEnums, EventsEnums> config)
        throws Exception {
        config
            .withConfiguration()
            .listener(listener());
    }

    /**
     * 用来创建StateMachineListener状态监听器的实例，在该实例中会定义具体的状态迁移处理逻辑，上面的实现中只是做了一些输出，
     * 实际业务场景会会有更负责的逻辑，所以通常情况下，我们可以将该实例的定义放到独立的类定义中，并用注入的方式加载进来。
     *
     * @return void
     */
    @Bean
    public StateMachineListener<StatusEnums, EventsEnums> listener() {
        return new StateMachineListenerAdapter<StatusEnums, EventsEnums>() {

            @Override
            public void transition(Transition<StatusEnums, EventsEnums> transition) {
                if (transition.getTarget().getId() == StatusEnums.UNPAID) {
                    log.info("订单创建，待支付");
                    return;
                }

                if (transition.getSource().getId() == StatusEnums.UNPAID
                    && transition.getTarget().getId() == StatusEnums.WAITING_FOR_RECEIVE) {
                    log.info("用户完成支付，待收货");
                    return;
                }

                if (transition.getSource().getId() == StatusEnums.WAITING_FOR_RECEIVE
                    && transition.getTarget().getId() == StatusEnums.DONE) {
                    log.info("用户已收货，订单完成");
                }
            }

        };
    }
}