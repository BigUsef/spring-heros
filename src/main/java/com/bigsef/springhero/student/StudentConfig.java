package com.bigsef.springhero.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository repository) {
        return args -> {
            Student s1 = new Student(
                    "Mahmud Youssef",
                    "mahmud.youssef@icloud.com",
                    LocalDate.of(1986, Month.OCTOBER, 1)
            );

            Student s2 = new Student(
                    "Bakr Youssef",
                    "b.youssef@icloud.com",
                    LocalDate.of(1995, Month.NOVEMBER, 12)
            );

            repository.saveAll(List.of(s1, s2));
        };
    }
}
