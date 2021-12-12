package com.budimirov.questionary;

import com.budimirov.questionary.domain.Question;
import com.budimirov.questionary.service.QuestionService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

@ComponentScan
@Configuration
@PropertySource("application.properties")
public class QuestionaryApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(QuestionaryApplication.class);
        QuestionService service = context.getBean(QuestionService.class);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Exam is passed: " + service.checkExam(scanner));
    }

}
