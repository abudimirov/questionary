package com.budimirov.questionary.service;

import com.budimirov.questionary.domain.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QuestionServiceImpl implements QuestionService{

    private String pathToFile;
    private final Map<Integer, Question> questionMap = new HashMap();

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public Question getById(int id) {
        return questionMap.getOrDefault(id, null);
    }

    @Override
    public Map<Integer, Question> getAllQuestions() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("questions.csv");
        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);
            int id = 0;
            for (String line; (line = reader.readLine()) != null;) {
                String[] lineArray = line.split(";");
                Question question = new Question(lineArray[0], Integer.parseInt(lineArray[1]));
                questionMap.put(id, question);
                id++;
            }
        }
        return questionMap;
    }
}
