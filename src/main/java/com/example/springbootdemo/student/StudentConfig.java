package com.example.springbootdemo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;
import static java.time.Month.AUGUST;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {

            Student kobe = new Student(
                    "Kobe",
                    "KBGoated@gmail.com",
                    LocalDate.of(2004, AUGUST, 5)
            );

            Student jania = new Student(
                    "Jania Don",
                    "jDon@gmail.com",
                    LocalDate.of(2000, AUGUST, 5)
            );

            repository.saveAll(
                    List.of(kobe, jania)
            );
        };
    }
}
