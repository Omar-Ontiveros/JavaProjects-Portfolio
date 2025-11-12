package OOP.JuegoCartas.app;

import OOP.JuegoCartas.games.BlackjackGame;
import OOP.JuegoCartas.games.PokerGame;
import java.util.Scanner;

public class CardGames {

    public static void main(String[] args) {
        int jugadores;
        int barajas;
        BlackjackGame bg = new BlackjackGame();
        PokerGame Pg = new PokerGame();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("=========== Bienvenido ===========");

            System.out.println("Elige un juego a continuación:");
            System.out.printf("1. Poker%n2. Blackjack%n3. Solitario%n4. Salir%n");
            int option = sc.nextInt();

            switch (option) {
                case 1 -> {
                    boolean volverMenuBJ = false;
                    while (!volverMenuBJ) {
                        System.out.printf("%n--- Poker --- %n1. Jugar%n2. Reglas%n3. Volver%n");
                        int bjOption = sc.nextInt();
                        switch (bjOption) {
                            case 1 -> {
                                System.out.println("Ingrese el número de jugadores entre 2 y 10: ");
                                jugadores = sc.nextInt();

                                if (jugadores < 2 || jugadores > 10) {
                                    System.out.println("⚠️ Error: el número de jugadores");
                                } else {
                                    Pg.playersAndDeck(jugadores);
                                }
                            }
                            case 2 -> {
                                System.out.printf("");
                                System.out.println("\nPresiona cualquier tecla para volver al menú de Poker.");
                                String tmp = sc.nextLine(); // esperar que el jugador confirme
                                sc.nextLine();
                            }
                            case 3 -> {
                                volverMenuBJ = true;
                            }
                            default ->
                                System.out.println("Opción inválida, intente de nuevo.");
                        }
                    }
                }
                case 2 -> {
                    boolean volverMenuBJ = false;
                    while (!volverMenuBJ) {
                        System.out.printf("%n--- Blackjack --- %n1. Jugar%n2. Reglas%n3. Volver%n");
                        int bjOption = sc.nextInt();
                        switch (bjOption) {
                            case 1 -> {
                                System.out.println("Ingrese el número de jugadores (max 7) y barajas (max 8):");
                                jugadores = sc.nextInt();
                                barajas = sc.nextInt();

                                if (jugadores < 1 || jugadores > 7 || barajas < 1 || barajas > 8) {
                                    System.out.println("⚠️ Error: el número de jugadores y barajas debe ser válido.");
                                } else {
                                    bg.playersAndDeck(jugadores, barajas);
                                }
                            }
                            case 2 -> {
                                System.out.printf(
                                        "Reglas de Blackjack (21):%n%n"
                                        + "1. Objetivo:%n"
                                        + "   - Acercarse lo más posible a 21 puntos sin pasarse.%n"
                                        + "   - Ganar al dealer (la banca).%n%n"
                                        + "2. Valores de las cartas:%n"
                                        + "   - 2–10: valor nominal%n"
                                        + "   - J, Q, K: 10 puntos%n"
                                        + "   - As: 1 u 11, según convenga al jugador%n%n"
                                        + "3. Número de jugadores:%n"
                                        + "   - Mínimo: 1%n"
                                        + "   - Máximo: 7%n%n"
                                        + "4. Número de barajas:%n"
                                        + "   - Mínimo: 1%n"
                                        + "   - Máximo: 8%n%n"
                                        + "5. Reparto inicial:%n"
                                        + "   - Cada jugador recibe 2 cartas%n"
                                        + "   - Dealer recibe 2 cartas (1 visible y 1 oculta)%n%n"
                                        + "6. Turnos del jugador:%n"
                                        + "   - Hit: pedir carta%n"
                                        + "   - Stand: quedarse con la mano%n"
                                        + "   - Double down: duplicar apuesta y recibir 1 carta más (opcional)%n"
                                        + "   - Split: dividir mano si las dos cartas son iguales (opcional)%n"
                                        + "   - La mano termina si se planta o se pasa de 21 (Bust)%n%n"
                                        + "7. Turno del dealer:%n"
                                        + "   - Revela carta oculta%n"
                                        + "   - Pide carta mientras puntos < 17%n"
                                        + "   - Se planta al llegar a 17–21%n"
                                        + "   - Si supera 21: Bust%n%n"
                                        + "8. Cálculo de puntos:%n"
                                        + "   - Sumar valores de cartas%n"
                                        + "   - Contar Ases como 11, pero ajustar a 1 si la suma > 21%n%n"
                                        + "9. Condiciones de victoria:%n"
                                        + "   - Blackjack: As + 10/J/Q/K en primeras 2 cartas → gana automáticamente%n"
                                        + "   - Si jugador ≤ 21 y dealer Bust → gana%n"
                                        + "   - Si puntos jugador > puntos dealer → gana%n"
                                        + "   - Si puntos jugador = puntos dealer → empate (Push)%n"
                                        + "   - Si puntos jugador < puntos dealer → pierde%n%n"
                                        + "10. Visibilidad de cartas:%n"
                                        + "    - Cada jugador ve solo su mano y la carta visible del dealer%n"
                                        + "    - Cartas de otros jugadores no se muestran%n"
                                );
                                System.out.println("\nPresiona cualquier tecla para volver al menú de Blackjack.");
                                String tmp = sc.nextLine(); // esperar que el jugador confirme
                                sc.nextLine();
                            }
                            case 3 -> {
                                volverMenuBJ = true;
                            }
                            default ->
                                System.out.println("Opción inválida, intente de nuevo.");
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Solitario seleccionado (a implementar).");
                }
                case 4 -> {
                    salir = true;
                    System.out.println("¡Gracias por jugar! Hasta luego.");
                }
                default ->
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        }
        sc.close();
    }
}
