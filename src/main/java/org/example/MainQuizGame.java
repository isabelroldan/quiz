package org.example;

import org.example.model.Player;
import org.example.model.Question;
import org.example.model.QuizGame;

import java.util.List;

public class MainQuizGame {
    public static void main(String[] args) {

        // Crear instancias de QuizGame, Player, etc.
        QuizGame quizGame = new QuizGame();
        Player player1 = new Player("Jugador 1");
        Player player2 = new Player("Jugador 2");

        // Configurar juego y jugadores
        quizGame.setPlayers(player1, player2);

        // Iniciar el juego
        quizGame.startGame();
    }
}