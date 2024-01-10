package org.example.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            // Manejo de errores: Puedes lanzar una excepción o tomar alguna otra medida según tus necesidades.
            throw new RuntimeException("Error al cargar las preguntas desde el archivo", e);
        }
        currentPlayerIndex = 0;
    }

    public void setPlayers(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() throws InterruptedException {
        Utilities.limpiarConsola();

        Scanner scan = new Scanner(System.in);
        System.out.print("Jugador uno introduzca su nombre: ");
        player1.setName(scan.nextLine());

        System.out.println("\n Bienvenido "+player1.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Utilities.limpiarConsola();

        System.out.print("Jugador dos introduzca su nombre: ");
        player2.setName(scan.nextLine());

        System.out.println("\n Bienvenido "+player2.getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<Thread> threads = new ArrayList<>();

        for (Question question : questions) {
            // Crear un hilo para cada pregunta y asignarle el jugador actual
            Thread thread = new Thread(() -> {
                try {
                    askQuestion(question);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
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

    private void askQuestion(Question question) throws InterruptedException {
        synchronized (this) {
            Utilities.limpiarConsola();
            System.out.println("    "+player1.getName()+": " + player1.getScore() + "P       "+player2.getName()+": " + player2.getScore() + "P\n");
            System.out.println("Pregunta: " + question.getQuestionText());
            for (int i = 0; i < question.getOptions().length; i++) {
                System.out.println("    "+(i + 1) + ".- " + question.getOptions()[i]);
            }

            Player currentPlayer = getCurrentPlayer();
            System.out.print("\n"+currentPlayer.getName() + ", elige la respuesta: ");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            // Validar la respuesta
            if (isValidInput(userInput)) {
                int selectedOption = Integer.parseInt(userInput) - 1;

                if (selectedOption == question.getCorrectOptionIndex()) {
                    System.out.println("\n ¡Respuesta correcta!");
                    currentPlayer.incrementScore();
                    Thread.sleep(1200);
                } else {
                    System.out.println("\n Respuesta incorrecta. La respuesta correcta era: " + (question.getCorrectOptionIndex() + 1));
                    currentPlayer.decrementScore();
                    Thread.sleep(1200);
                }
            } else {
                System.out.println("\n Entrada no válida. Debes elegir una opción válida.");
                Thread.sleep(1200);
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

    private void displayFinalResults() throws InterruptedException {
        String winner = "Winner";
        int winnerScore = 0;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Utilities.limpiarConsola();
        System.out.println("Fin del juego. Resultados:\n");
        System.out.println(" "+player1.getName() + ": " + player1.getScore() + " puntos");
        System.out.println(" "+player2.getName() + ": " + player2.getScore() + " puntos\n");

        if (player1.getScore() > player2.getScore()) {
            System.out.println(" "+player1.getName() + " gana con " + player1.getScore() + " puntos!\n");
            winner = player1.getName();
            winnerScore = player1.getScore();
        } else if (player1.getScore() < player2.getScore()) {
            System.out.println(" "+player2.getName() + " gana con " + player2.getScore() + " puntos!\n");
            winner = player2.getName();
            winnerScore = player2.getScore();
        } else {
            System.out.println("¡Empate!\n");
        }
        System.out.println("¿"+winner + " quieres guardar la puntuación? \"Y\" para si \"N\" para no");
        Scanner scan = new Scanner(System.in);
        String resp = scan.nextLine();
        if (resp.equals("Y")){
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("scoreboard.txt", true)))) {
                out.println(winner + " - " + winnerScore);
                System.out.println("Puntuacion guardada con exito");
                out.close();
                Thread.sleep(500);
            } catch (IOException e) {
                System.err.println("Error al guardar los resultados en el archivo: " + e.getMessage());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (resp.equals("N")) {
            System.out.println("");
        } else {
            System.out.println("Debe escribir una opción valida");
        }
    }
}