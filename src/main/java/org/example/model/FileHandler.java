package org.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<Question> loadQuestionsFromFile(String filePath) throws IOException {
        List<Question> questions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Procesar línea y crear objetos Question
                // Agregar objetos Question a la lista


            }
        }

        return questions;
    }

    public static void writeResultsToFile(String filePath, List<Player> players) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Player player : players) {
                writer.write(player.getName() + ": " + player.getScore() + " puntos");
                writer.newLine();
            }
        }
    }
}

