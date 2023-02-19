package com.github.nikolasfunction.tournamentobserver.telegram.state;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.data.jpa.JpaPersistingStateMachineInterceptor;
import org.springframework.statemachine.data.jpa.JpaStateMachineRepository;
import org.springframework.statemachine.persist.StateMachineRuntimePersister;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

@Configuration
public class PersistenceConfiguration {
    
    @Bean
    public StateMachineService<UserState, UserEvent> stateMachineService(
            final StateMachineFactory<UserState, UserEvent> stateMachineFactory,
            final StateMachinePersist<UserState, UserEvent, String> stateMachinePersist) {
        return new DefaultStateMachineService<>(stateMachineFactory, stateMachinePersist);
    }
    
    @Bean
    public StateMachineRuntimePersister<UserState, UserEvent, String> stateMachineRuntimePersister(
                    JpaStateMachineRepository jpaStateMachineRepository) {
            return new JpaPersistingStateMachineInterceptor<>(jpaStateMachineRepository);
    }

}
