package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Preguntas de conocimiento general

        // Pregunta 1
        List<String> options1 = new ArrayList<>();
        options1.add("a) Opción A");
        options1.add("b) Opción B");
        options1.add("c) Opción C");
        options1.add("d) Opción D");
        Question question1 = new Question("¿En qué año se fundó la ciudad de Roma?", options1, 1, 10);
        questions.add(question1);

        // Pregunta 2
        List<String> options2 = new ArrayList<>();
        options2.add("a) Opción A");
        options2.add("b) Opción B");
        options2.add("c) Opción C");
        options2.add("d) Opción D");
        Question question2 = new Question("¿Cuál es la capital de Francia?", options2, 2, 15);
        questions.add(question2);

        // Pregunta 3
        List<String> options3 = new ArrayList<>();
        options3.add("a) Opción A");
        options3.add("b) Opción B");
        options3.add("c) Opción C");
        options3.add("d) Opción D");
        Question question3 = new Question("¿Cuál es el océano más grande?", options3, 3, 20);
        questions.add(question3);

        // Y así sucesivamente...
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
