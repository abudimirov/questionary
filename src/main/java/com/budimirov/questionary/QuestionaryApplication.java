package com.budimirov.questionary;

import com.budimirov.questionary.domain.Question;
import com.budimirov.questionary.service.QuestionService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

public class QuestionaryApplication {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        Map<Integer, Question> questionMap = service.getAllQuestions();
        questionMap.forEach((key, value) -> System.out.println(value.getQuestionBody()));
    }

}
