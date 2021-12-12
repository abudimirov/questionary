package com.budimirov.questionary.service;

import com.budimirov.questionary.domain.Question;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public interface QuestionService {
    Map<Integer, Question> getAllQuestions() throws IOException;
    boolean askQuestion(Scanner scanner, Question question);
    boolean checkExam(Scanner scanner);
}
