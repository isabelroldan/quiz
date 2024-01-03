package org.example.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionText;
    private String[] options;
    private int correctOptionIndex;

    public Question(String questionText, String[] options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }



    public String[] getOptions() {
        return options;
    }


    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }


    public static List<Question> loadQuestionsFromFile(String fileName) throws IOException {
        List<Question> questions = new ArrayList<>();

        try (InputStream inputStream = Question.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Asumiendo que cada línea tiene 5 partes
                    String questionText = parts[0];
                    String[] options = { parts[1], parts[2], parts[3] };
                    int correctOptionIndex = Integer.parseInt(parts[4]);

                    Question question = new Question(questionText, options, correctOptionIndex);
                    questions.add(question);
                } else {
                    System.out.println("Formato incorrecto en la línea: " + line);
                }
            }
        }

        return questions;
    }
}

