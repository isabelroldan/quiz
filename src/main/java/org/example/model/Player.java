package org.example.model;

import java.util.Scanner;

public class Player {
    private String name;
    private int score;

    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public String provideAnswer() {
        System.out.print(name + ", ingresa tu respuesta (a, s, d): ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase(); // Convertir a minúsculas para manejar respuestas en cualquier caso
    }
}
