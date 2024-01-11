package org.example.interfaces;

import org.example.model.Player;
import org.example.model.QuizGame;
import org.example.model.Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void menu() throws InterruptedException {
        int op = 0;
        Scanner scan = new Scanner(System.in);

        while (true) {
            Utilities.limpiarConsola();

            System.out.println("\n+-----------------------------------------------------------------------------+");
            System.out.println("|       _____    _   _  U _____ u       ___     _   _             _____       |");
            System.out.println("|      |_ \" _|  |'| |'| \\| ___\"|/      / \" \\ U |\"|u| |   ___     |\"_  /u      |");
            System.out.println("|        | |   /| |_| |\\ |  _|\"       | |\"| | \\| |\\| |  |_\"_|    U / //       |");
            System.out.println("|       /| |\\  U|  _  |u | |___      /| |_| |\\ | |_| |   | |     \\/ /_        |");
            System.out.println("|      u |_|U   |_| |_|  |_____|     U \\__\\_\\u<<\\___/  U/| |\\u   /____|       |");
            System.out.println("|      _// \\\\_  //   \\\\  <<   >>        \\\\// (__) )(.-,_|___|_,-._//<<,-      |");
            System.out.println("|     (__) (__)(_\") (\"_)(__) (__)      (_(__)    (__)\\_)-' '-(_/(__) (_/      |");
            System.out.println("|                    ____      _      __  __  U _____ u                       |");
            System.out.println("|                 U /\"___|uU  /\"\\  uU|' \\/ '|u\\| ___\"|/                       |");
            System.out.println("|                 \\| |  _ / \\/ _ \\/ \\| |\\/| |/ |  _|\"                         |");
            System.out.println("|                  | |_| |  / ___ \\  | |  | |  | |___                         |");
            System.out.println("|                   \\____| /_/   \\_\\ |_|  |_|  |_____|                        |");
            System.out.println("|                   _)(|_   \\\\    >><<,-,,-.   <<   >>                        |");
            System.out.println("|                  (__)__) (__)  (__)(./  \\.) (__) (__)                       |");
            System.out.println("+-----------------------------------------------------------------------------+\n");

            System.out.print("1.- Jugar\n2.- Scoreboard\n3.- Salir\n\nEscoja una opción: ");

            try {
                op = scan.nextInt();
            } catch (Exception e) {
                System.out.println("La opción elegida debe ser un número entero");
            }

            switch (op) {
                case 1:
                    // Crear instancias de QuizGame, Player, etc.
                    QuizGame quizGame = new QuizGame();
                    Player player1 = new Player("Jugador 1");
                    Player player2 = new Player("Jugador 2");

                    // Configurar juego y jugadores
                    quizGame.setPlayers(player1, player2);

                    // Iniciar el juego
                    quizGame.startGame();
                    break;
                case 2:
                    Utilities.limpiarConsola();
                    System.out.println("\n+------------------------------------------------------------------+");
                    System.out.println("|       _____                    _                         _       |");
                    System.out.println("|      /  ___|                  | |                       | |      |");
                    System.out.println("|      \\ `--.  ___ ___  _ __ ___| |__   ___   __ _ _ __ __| |      |");
                    System.out.println("|       `--. \\/ __/ _ \\| '__/ _ \\ '_ \\ / _ \\ / _` | '__/ _` |      |");
                    System.out.println("|      /\\__/ / (_| (_) | | |  __/ |_) | (_) | (_| | | | (_| |      |");
                    System.out.println("|      \\____/ \\___\\___/|_|  \\___|_.__/ \\___/ \\__,_|_|  \\__,_|      |");
                    System.out.println("|                                                                  |");
                    System.out.println("+------------------------------------------------------------------+\n");

                    List<Player> players = new ArrayList<>();

                    try {
                        BufferedReader reader = new BufferedReader(new FileReader("scoreboard.txt"));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.split(",");
                            String name = parts[0];
                            int score = Integer.parseInt(parts[1]);
                            Player player = new Player(name);
                            player.setScore(score);
                            players.add(player);
                        }
                        reader.close();
                    } catch (IOException e) {
                        System.out.println("Ha ocurrido un error al leer el archivo scoreboard.txt");
                        e.printStackTrace();
                    }

                    players.sort((p1, p2) -> Integer.compare(p2.getScore(), p1.getScore()));

                    System.out.println("\n+------------------------------------------+");
                    System.out.println("| Indice | Nombre del Jugador | Puntuación |");
                    System.out.println("+------------------------------------------+");

                    for (int i = 0; i < Math.min(10, players.size()); i++) {
                        System.out.printf("| %-6d | %-18s | %-10d |\n", i + 1, players.get(i).getName(), players.get(i).getScore());
                        System.out.println("+------------------------------------------+");
                    }

                    System.out.println("\nPresiona cualquier tecla para continuar...");
                    Scanner scanner = new Scanner(System.in);
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("\nGracias por jugar, adios.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Escoja una opción valida del menú, de 1 a 4");
                    Thread.sleep(1000);
                    break;
            }
        }
    }
}
