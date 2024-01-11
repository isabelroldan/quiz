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

    /**
     * Carga preguntas desde un archivo de texto.
     *
     * @param fileName El nombre del archivo que contiene las preguntas.
     * @return Una lista de objetos Question que representan las preguntas cargadas.
     * @throws IOException Se lanza si hay un error al leer el archivo.
     */
    public static List<Question> loadQuestionsFromFile(String fileName) throws IOException {
        // Lista para almacenar las preguntas cargadas desde el archivo.
        List<Question> questions = new ArrayList<>();

        try (InputStream inputStream = Question.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            // Leer cada línea del archivo.
            while ((line = reader.readLine()) != null) {
                // Dividir la línea en partes utilizando coma como delimitador.
                String[] parts = line.split(",");
                if (parts.length == 5) { // Asumiendo que cada línea tiene 5 partes
                    // Extraer la pregunta, opciones y el índice de la opción correcta.
                    String questionText = parts[0];
                    String[] options = { parts[1], parts[2], parts[3] };
                    int correctOptionIndex = Integer.parseInt(parts[4]);

                    // Crear un objeto Question y agregarlo a la lista.
                    Question question = new Question(questionText, options, correctOptionIndex);
                    questions.add(question);
                } else {
                    // Imprimir un mensaje si el formato de la línea es incorrecto.
                    System.out.println("Formato incorrecto en la línea: " + line);
                }
            }
        }

        // Devolver la lista de preguntas cargadas desde el archivo.
        return questions;
    }
}

