package com.budimirov.questionary.domain;

import lombok.Data;

@Data
public class Question {
    private final String questionBody;
    private final int correctAnswer;
}
