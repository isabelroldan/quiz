package org.example.model;

import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;
    private int timeLimit;

    public Question(String questionText, List<String> options, int correctAnswer, int timeLimit) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.timeLimit = timeLimit;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public String toString() {
        return "Question{" + "questionText='" + questionText + '\'' + ", options=" + options + ", correctAnswer=" + correctAnswer + ", timeLimit=" + timeLimit + '}';
    }
}
