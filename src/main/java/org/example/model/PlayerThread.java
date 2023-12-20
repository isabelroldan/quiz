package org.example.model;

import java.util.List;

public class PlayerThread extends Thread {
    private Player player;
    private Quiz quiz;

    // Constructor
    public PlayerThread(Player player, Quiz quiz) {
        this.player = player;
        this.quiz = quiz;
    }

    public Player getPlayer() {
        return player;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void addQuestion(Question question) {
        quiz.addQuestion(question);
    }

    public void addPlayer(Player player) {
        quiz.addPlayer(player);
    }

    public void removeQuestion(Question question) {
        quiz.removeQuestion(question);
    }

    @Override
    public void run() {
        while (quiz.getQuestions().size() > 0) {
            Question question = quiz.getQuestions().get(0);
            if (question.getTimeLimit() > 0) {
                try {
                    Thread.sleep(question.getTimeLimit() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

        }


    }
}