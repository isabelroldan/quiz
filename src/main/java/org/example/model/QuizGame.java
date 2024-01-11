package org.example.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizGame {
    private List<Question> questions;
    private Player player1;
    private Player player2;
    private int currentPlayerIndex;

    /**
     * Constructor de la clase QuizGame. Inicializa el juego cargando preguntas desde un archivo.
     * Si ocurre un error durante la carga, lanza una excepción.
     */
    public QuizGame() {
        // Cargar preguntas desde el archivo al iniciar el juego
        try {
            // Cargar preguntas desde el archivo "questions.txt"
            questions = Question.loadQuestionsFromFile("questions.txt");

            // Mezclar las preguntas para mayor aleatoriedad.
            Collections.shuffle(questions);

            // Seleccionar las primeras 10 preguntas después de mezclar.
            questions = questions.subList(0, 10);
        } catch (IOException e) {
            // Lanzar una excepción en caso de error durante la carga de preguntas.
            throw new RuntimeException("Error al cargar las preguntas desde el archivo", e);
        }

        // Inicializar el índice del jugador actual.
        currentPlayerIndex = 0;
    }

    /**
     * Establece los jugadores para el juego.
     *
     * @param player1 El primer jugador.
     * @param player2 El segundo jugador.
     */
    public void setPlayers(Player player1, Player player2) {
        // Asignar el primer jugador al campo 'player1'.
        this.player1 = player1;

        // Asignar el segundo jugador al campo 'player2'.
        this.player2 = player2;
    }

    /**
     * Inicia el juego, solicitando nombres de jugadores, presentando preguntas y mostrando los resultados finales.
     *
     * @throws InterruptedException Se lanza si hay un problema con las interrupciones de hilos.
     */
    public void startGame() throws InterruptedException {
        // Limpiar la consola antes de empezar el juego.
        Utilities.limpiarConsola();

        // Configurar el nombre del Jugador 1.
        Scanner scan = new Scanner(System.in);
        System.out.print("Jugador uno introduzca su nombre: ");
        player1.setName(scan.nextLine());

        // Mostrar mensaje de bienvenida al Jugador 1.
        System.out.println("\n Bienvenido "+player1.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Limpiar la consola antes de configurar el nombre del Jugador 2.
        Utilities.limpiarConsola();

        // Configurar el nombre del Jugador 2.
        System.out.print("Jugador dos introduzca su nombre: ");
        player2.setName(scan.nextLine());

        // Mostrar mensaje de bienvenida al Jugador 2.
        System.out.println("\n Bienvenido "+player2.getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Lista para almacenar los hilos de preguntas.
        List<Thread> threads = new ArrayList<>();

        // Iterar sobre cada pregunta y crear un hilo para cada una.
        for (Question question : questions) {
            // Crear un hilo para cada pregunta y asignarle el jugador actual
            Thread thread = new Thread(() -> {
                try {
                    askQuestion(question);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            // Agregar el hilo a la lista.
            threads.add(thread);

            // Iniciar el hilo.
            thread.start();
        }

        // Esperar a que todos los hilos terminen antes de mostrar los resultados finales
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // Manejar la interrupción del hilo actual.
                Thread.currentThread().interrupt();
            }
        }

        // Mostrar los resultados finales del juego.
        displayFinalResults();
    }

    /**
     * Presenta una pregunta al jugador actual y gestiona la interacción del usuario.
     * Actualiza los puntajes y cambia al siguiente jugador después de cada pregunta.
     *
     * @param question La pregunta que se presenta al jugador.
     * @throws InterruptedException Se lanza si hay un problema con las interrupciones de hilos.
     */
    private void askQuestion(Question question) throws InterruptedException {
        // Sincronizar para evitar conflictos de acceso a recursos compartidos.
        synchronized (this) {
            // Limpiar la consola antes de mostrar la pregunta.
            Utilities.limpiarConsola();

            // Mostrar puntajes actuales de ambos jugadores.
            System.out.println("    "+player1.getName()+": " + player1.getScore() + "P       "+player2.getName()+": " + player2.getScore() + "P\n");

            // Mostrar la pregunta y sus opciones.
            System.out.println("Pregunta: " + question.getQuestionText());
            for (int i = 0; i < question.getOptions().length; i++) {
                System.out.println("    "+(i + 1) + ".- " + question.getOptions()[i]);
            }

            // Obtener el jugador actual.
            Player currentPlayer = getCurrentPlayer();
            System.out.print("\n"+currentPlayer.getName() + ", elige la respuesta: ");

            // Leer la respuesta del jugador desde la entrada estándar.
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            // Validar la respuesta ingresada por el jugador
            if (isValidInput(userInput)) {
                int selectedOption = Integer.parseInt(userInput) - 1;

                // Verificar si la respuesta es correcta y actualizar el puntaje.
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
                // Manejar entrada no válida y volver a preguntar.
                System.out.println("\n Entrada no válida. Debes elegir una opción válida.");
                Thread.sleep(1200);
                askQuestion(question);
            }

            // Cambiar al siguiente jugador después de cada pregunta
            switchPlayer();
        }
    }

    /**
     * Cambia al siguiente jugador alternando entre los jugadores 1 y 2.
     */
    private void switchPlayer() {
        // Sincronizar para evitar conflictos de acceso a recursos compartidos.
        synchronized (this) {
            // Alternar entre 0 y 1 intercambiando el índice del jugador actual.
            currentPlayerIndex = 1 - currentPlayerIndex; // Alternar entre 0 y 1
        }
    }

    /**
     * Verifica si la entrada del usuario es válida para la selección de opciones.
     *
     * @param userInput La entrada del usuario a validar.
     * @return true si la entrada es un número entre 1 y 3 (ambos inclusive), false de lo contrario.
     */
    private boolean isValidInput(String userInput) {
        try {
            // Intentar convertir la entrada del usuario a un número entero.
            int selectedOption = Integer.parseInt(userInput);

            // Verificar si el número está en el rango válido (1 a 3).
            return selectedOption >= 1 && selectedOption <= 3;
        } catch (NumberFormatException e) {
            // Capturar la excepción si la entrada no es un número.
            return false;
        }
    }

    /**
     * Obtiene el jugador actual basado en el índice del jugador actual.
     *
     * @return El jugador actual.
     */
    private Player getCurrentPlayer() {
        // Devolver player1 si el índice del jugador actual es 0, de lo contrario, devolver player2.
        return (currentPlayerIndex == 0) ? player1 : player2;
    }

    /**
     * Muestra los resultados finales del juego, incluyendo los puntajes de los jugadores y al ganador.
     * Permite al ganador guardar su puntuación en un archivo.
     *
     * @throws InterruptedException Se lanza si hay un problema con las interrupciones de hilos.
     */
    private void displayFinalResults() throws InterruptedException {
        // Variables para almacenar el nombre del ganador y su puntaje.
        String winner = "Winner";
        int winnerScore = 0;

        try {
            // Esperar antes de mostrar los resultados finales.
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Limpiar la consola antes de mostrar los resultados finales.
        Utilities.limpiarConsola();
        System.out.println("Fin del juego. Resultados:\n");
        System.out.println(" "+player1.getName() + ": " + player1.getScore() + " puntos");
        System.out.println(" "+player2.getName() + ": " + player2.getScore() + " puntos\n");

        // Determinar al ganador comparando los puntajes de los jugadores
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

        // Solicitar al ganador si desea guardar la puntuación.
        System.out.println("¿"+winner + " quieres guardar la puntuación? \"Y\" para si \"N\" para no");
        Scanner scan = new Scanner(System.in);
        String resp = scan.nextLine();

        if (resp.equals("Y")){
            // Guardar la puntuación del ganador en un archivo.
            try {
                PrintWriter out = new PrintWriter(new FileWriter("scoreboard.txt", true));
                out.println(winner + "," + winnerScore);
                out.close();
                System.out.println("Puntuación guardada");
                Thread.sleep(500);
            } catch (IOException e) {
                // Manejar errores al intentar guardar la puntuación.
                System.err.println("Error al guardar los resultados en el archivo: " + e.getMessage());
            }
        } else if (resp.equals("N")) {
            // No hacer nada si el ganador decide no guardar la puntuación.
            System.out.println("");
        } else {
            // Manejar entrada no válida si el ganador no elige "Y" o "N".
            System.out.println("Debe escribir una opción valida");
        }
    }
}