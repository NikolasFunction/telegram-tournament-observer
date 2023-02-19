package com.github.nikolasfunction.tournamentobserver.telegram.state;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;

@Configuration
@EnableStateMachineFactory
public class UserStateMachine extends EnumStateMachineConfigurerAdapter<UserState, UserEvent> {
    
    private StateMachineRuntimePersister<UserState, UserEvent, String> stateMachineRuntimePersister;
    
    @Autowired    
    public UserStateMachine(
            StateMachineRuntimePersister<UserState, UserEvent, String> stateMachineRuntimePersister) {
        this.stateMachineRuntimePersister = stateMachineRuntimePersister;
    }

    @Override
    public void configure(StateMachineStateConfigurer<UserState, UserEvent> states) throws Exception {
        states
          .withStates()
          .initial(UserState.STATE_INIT)
          .states(EnumSet.allOf(UserState.class));
    }
    
    @Override
    public void configure(
      StateMachineTransitionConfigurer<UserState, UserEvent> transitions) throws Exception {
        
        Action<String, String> action = context -> System.out.println(context.getTarget().getId());
        Action<String, String> error = context -> System.out.println("Error " + context.getSource().getId() + context.getException());
        
        transitions.withExternal()
                .source(UserState.STATE_INIT).event(UserEvent.COMMAND_OBSERVE).target(UserState.STATE_OBSERVE).and()
                .withExternal()
                .source(UserState.STATE_OBSERVE).event(UserEvent.TEXT_TOURNAMENT).target(UserState.STATE_INIT).and()
                .withExternal()
                .source(UserState.STATE_OBSERVE).event(UserEvent.COMMAND_EXIT).target(UserState.STATE_OBSERVE);
    }
    
    @Override
    public void configure(StateMachineConfigurationConfigurer<UserState, UserEvent> config) throws Exception {
        config.withPersistence().runtimePersister(stateMachineRuntimePersister);
    }
    
}
