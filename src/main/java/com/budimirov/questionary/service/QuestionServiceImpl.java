package com.budimirov.questionary.service;

import com.budimirov.questionary.domain.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Value("${questions.file}")
    private String pathToFile;

    @Value("${successRate}")
    private String successRate;

    private final Map<Integer, Question> questionMap = new HashMap();

    @Override
    public boolean checkExam(Scanner scanner) {
        AtomicInteger correctAnswers = new AtomicInteger();
        System.out.println("Please, state your name:");
        String student = scanner.nextLine();
        System.out.printf("%s, write your answer after every question and hit Enter. Pass rate is %s percent of %d questions\n", student, successRate, questionMap.size() );
        getAllQuestions().forEach((integer, question) -> correctAnswers.addAndGet(askQuestion(scanner, question) ? 1 : 0));
        System.out.printf("Correct answers is %d out of %d questions\n",correctAnswers.get(),questionMap.size());
        int actualRate = correctAnswers.get() / questionMap.size() * 100;
        return actualRate >= Integer.parseInt(successRate);
    }

    @Override
    public Map<Integer, Question> getAllQuestions() {
        try (Reader reader = Files.newBufferedReader(Paths.get(pathToFile))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records) {
                Question question = new Question(record.get(0), Integer.parseInt(record.get(1)));
                questionMap.put((int) record.getRecordNumber(), question);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return questionMap;
    }

    @Override
    public boolean askQuestion(Scanner scanner, Question question) {
        System.out.println("Question: " + question.getQuestionBody());
        boolean isCorrect;
        if (scanner.hasNextInt()) {
            isCorrect = scanner.nextInt() == question.getCorrectAnswer();
        } else throw new IllegalArgumentException("Wrong input. Not a number");
        return isCorrect;
    }
}
