package com.budimirov.questionary.service;

import com.budimirov.questionary.domain.Question;

import java.io.IOException;
import java.util.Map;

public interface QuestionService {
    Question getById(int id);
    Map<Integer, Question> getAllQuestions() throws IOException;
}
