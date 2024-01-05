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
        score = score + 100;
    }

    public void decrementScore() {
    if (score > 0) score = score - 50;
    }
}
