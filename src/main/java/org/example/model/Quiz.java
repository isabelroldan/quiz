package org.example.model;

import java.util.List;
import java.util.Random;

public class Quiz {
    private List<Question> questions;
    private List<Player> players;

    public Quiz(List<Question> questions, List<Player> players) {
        this.questions = questions;
        this.players = players;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void removeQuestion(int index) {
        questions.remove(index);
    }

    public void removePlayer(int index) {
        players.remove(index);
    }

    public int getNumberOfQuestions() {
        return questions.size();
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public void startGame() {
        // Lógica del juego
    }

    private Question getRandomQuestion() {
        Random random = new Random();
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }
}
