package org.example.interfaces;

import org.example.model.Player;
import org.example.model.QuizGame;
import org.example.model.Utilities;

import java.util.Scanner;

public class Menu {
    public static void menu() {
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

        System.out.print("1.- Jugar\n2.- Scoreboard\n3.- Instrucciones\n4.- Salir\n\nEscoja una opción: ");

        int op = 0;

        Scanner scan = new Scanner(System.in);
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
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            default:
                System.out.println("Escoja una opción valida del menú, de 1 a 4");
                break;
        }
    }
}
