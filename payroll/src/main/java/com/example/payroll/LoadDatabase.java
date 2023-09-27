package com.example.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 스프링에게 구성요소로 추가하라고 알려준다
@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //스프링에서 관리할 빈을 생성하라고 알려준다
    //EmployeeRepository를 매개변수로 받고 log로 repository.save에서 새로운 emp객체를 생성해 로그를 띄워준다
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository) {
        return args -> {
            log.info("Preloading " +repository.save(new Employee("Bilbo Baggins", "burglar")) );
            log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
        };
    }
}
