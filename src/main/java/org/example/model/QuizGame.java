package org.example.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGame {
    private List<Question> questions;
    private Player player1;
    private Player player2;
    private int currentPlayerIndex;

    public QuizGame() {
        // Cargar preguntas desde el archivo al iniciar el juego
        try {
            questions = Question.loadQuestionsFromFile("questions.txt");
        } catch (IOException e) {
            //e.printStackTrace();

            // Manejo de errores: Puedes lanzar una excepción o tomar alguna otra medida según tus necesidades.
            throw new RuntimeException("Error al cargar las preguntas desde el archivo", e);

        }

        currentPlayerIndex = 0;
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /*public void startGame() {
        for (Question question : questions) {
            askQuestion(question);
            switchPlayer();
        }

        displayFinalResults();
    }

    private void askQuestion(Question question) {
        System.out.println("Pregunta: " + question.getQuestionText());
        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + question.getOptions()[i]);
        }

        Player currentPlayer = getCurrentPlayer();
        System.out.print(currentPlayer.getName() + ", elige la respuesta: ");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Validar la respuesta
        if (isValidInput(userInput)) {
            int selectedOption = Integer.parseInt(userInput) - 1;

            if (selectedOption == question.getCorrectOptionIndex()) {
                System.out.println("¡Respuesta correcta!");
                currentPlayer.incrementScore();
            } else {
                System.out.println("Respuesta incorrecta. La respuesta correcta era: " + (question.getCorrectOptionIndex() + 1));
            }
        } else {
            System.out.println("Entrada no válida. Debes elegir una opción válida.");
            askQuestion(question);
        }
    }

    private boolean isValidInput(String userInput) {
        try {
            int selectedOption = Integer.parseInt(userInput);
            return selectedOption >= 1 && selectedOption <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex; // Alternar entre 0 y 1
    }

    private Player getCurrentPlayer() {
        return (currentPlayerIndex == 0) ? player1 : player2;
    }

    private void displayFinalResults() {
        System.out.println("Fin del juego. Resultados:");
        System.out.println(player1.getName() + ": " + player1.getScore() + " puntos");
        System.out.println(player2.getName() + ": " + player2.getScore() + " puntos");

        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " gana!");
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println(player2.getName() + " gana!");
        } else {
            System.out.println("¡Empate!");
        }
    }*/

    public void startGame() {
        List<Thread> threads = new ArrayList<>();

        for (Question question : questions) {
            // Crear un hilo para cada pregunta y asignarle el jugador actual
            Thread thread = new Thread(() -> askQuestion(question));
            threads.add(thread);
            thread.start();
        }

        // Esperar a que todos los hilos terminen antes de mostrar los resultados finales
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        displayFinalResults();
    }

    private void askQuestion(Question question) {
        synchronized (this) {
            System.out.println("Pregunta: " + question.getQuestionText());
            for (int i = 0; i < question.getOptions().length; i++) {
                System.out.println((i + 1) + ". " + question.getOptions()[i]);
            }

            Player currentPlayer = getCurrentPlayer();
            System.out.print(currentPlayer.getName() + ", elige la respuesta: ");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            // Validar la respuesta
            if (isValidInput(userInput)) {
                int selectedOption = Integer.parseInt(userInput) - 1;

                if (selectedOption == question.getCorrectOptionIndex()) {
                    System.out.println("¡Respuesta correcta!");
                    currentPlayer.incrementScore();
                } else {
                    System.out.println("Respuesta incorrecta. La respuesta correcta era: " + (question.getCorrectOptionIndex() + 1));
                }
            } else {
                System.out.println("Entrada no válida. Debes elegir una opción válida.");
                askQuestion(question);
            }

            // Cambiar al siguiente jugador después de cada pregunta
            switchPlayer();
        }
    }

    private void switchPlayer() {
        synchronized (this) {
            currentPlayerIndex = 1 - currentPlayerIndex; // Alternar entre 0 y 1
        }
    }

    private boolean isValidInput(String userInput) {
        try {
            int selectedOption = Integer.parseInt(userInput);
            return selectedOption >= 1 && selectedOption <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private Player getCurrentPlayer() {
        return (currentPlayerIndex == 0) ? player1 : player2;
    }

    private void displayFinalResults() {
        System.out.println("Fin del juego. Resultados:");
        System.out.println(player1.getName() + ": " + player1.getScore() + " puntos");
        System.out.println(player2.getName() + ": " + player2.getScore() + " puntos");

        if (player1.getScore() > player2.getScore()) {
            System.out.println(player1.getName() + " gana!");
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println(player2.getName() + " gana!");
        } else {
            System.out.println("¡Empate!");
        }
    }

}