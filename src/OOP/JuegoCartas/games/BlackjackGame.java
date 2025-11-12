package OOP.JuegoCartas.games;

import OOP.JuegoCartas.interfaces.GameActions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame implements GameActions {

    private List<String> baraja = new ArrayList<>();
    private List<List<String>> manosJugadores = new ArrayList<>(); // cada jugador tiene su mano
    private List<List<String>> manoDealer = new ArrayList<>();
    private int totalJugadores = 1;
    private int totalBarajas = 1;
    private int opcion = 0;

    public void playersAndDeck(int nJugador, int nBarajas) {
        this.totalJugadores = nJugador;
        this.totalBarajas = nBarajas;
        shuffle();
    }

    @Override
    public void shuffle() {
        String[] palos = {"♠", "♥", "♦", "♣"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        baraja.clear();
        for (int i = 0; i < totalBarajas; i++) {
            for (String palo : palos) {
                for (String valor : valores) {
                    baraja.add(valor + " " + palo);
                }
            }
        }
        Collections.shuffle(baraja);
        dealCards();
    }

    // Reparte dos cartas iniciales a cada jugador
    @Override
    public void dealCards() {
        manosJugadores.clear();
        List<String> mano;
        for (int i = 0; i < totalJugadores; i++) {
            mano = new ArrayList<>(baraja.subList(0, 2));
            baraja.subList(0, 2).clear();
            manosJugadores.add(mano);
        }
        mano = new ArrayList<>(baraja.subList(0, 2));
        baraja.subList(0, 2).clear();
        manoDealer.add(mano);
        showCards();
        playerAction();
    }

    // Reparte una carta específica a un jugador
    private void dealCardToPlayer(int indiceJugador) {
        if (!baraja.isEmpty()) {
            String carta = baraja.remove(0);
            manosJugadores.get(indiceJugador).add(carta);
        }
    }

    private void dealCardToDealer() {
        while (!baraja.isEmpty()) {
            int puntos = calculatePointsDealer();

            // El dealer pide carta mientras tenga menos de 17
            if (puntos < 17) {
                String carta = baraja.remove(0);
                manoDealer.get(0).add(carta);
            } else {
                break; // se planta
            }
        }
    }

    // Muestra todas las cartas de los jugadores por consola
    @Override
    public void showCards() {
        System.out.println("\nCartas del Dealer:");
        System.out.print("Dealer [" + manoDealer.get(0).get(0) + ", ???]");
        System.out.println("\nCartas de los jugadores:");
        for (int i = 0; i < totalJugadores; i++) {
            System.out.println("Jugador " + (i + 1) + ": " + manosJugadores.get(i) + " -> " + calculatePoints(i) + " puntos");
        }
    }

    // Acciones disponibles para el jugador
    @Override
    public void playerAction() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < totalJugadores; i++) {
            boolean turnoActivo = true;

            while (turnoActivo) {
                if (calculatePoints(i) > 21) {
                    break;
                }
                System.out.println("\nJugador " + (i + 1) + ", elige una opción:");
                System.out.printf("1. Hit%n2. Stand%n3. Double down%n4. Split%n5. Salir%n");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1 -> {
                        dealCardToPlayer(i);
                        System.out.println("Tu mano ahora: " + manosJugadores.get(i) + " -> " + calculatePoints(i) + " puntos");
                    }
                    case 2 ->
                        turnoActivo = false; // pasa turno
                    case 3 -> {
                        dealCardToPlayer(i);
                        System.out.println("Double down: tu mano ahora: " + manosJugadores.get(i) + " -> " + calculatePoints(i) + " puntos");
                        turnoActivo = false;
                    }
                    case 4 -> {
                        realizarSplit(i);
                        turnoActivo = false;
                    }
                    case 5 -> {
                        System.out.println("Jugador " + (i + 1) + " sale del juego");
                        turnoActivo = false;
                    }
                    default ->
                        System.out.println("Opción inválida");
                }
            }
        }
        showCards();
        checkGameOver();
    }

    // Calcula el valor total de la mano del jugador
    public int calculatePoints(int indiceJugador) {
        List<String> mano = manosJugadores.get(indiceJugador);
        int valorTotal = 0;
        int cantidadAses = 0;

        for (String carta : mano) {
            String[] partes = carta.split("[♠♥♦♣ ]+");
            String valor = partes[0];

            if (Character.isDigit(valor.charAt(0))) {
                valorTotal += Integer.parseInt(valor);
            } else {
                switch (valor) {
                    case "J", "Q", "K" ->
                        valorTotal += 10;
                    case "A" -> {
                        valorTotal += 11;
                        cantidadAses++;
                    }
                    default ->
                        throw new IllegalArgumentException("Valor desconocido: " + valor);
                }
            }
        }

        // Ajusta el valor del As si supera 21
        while (valorTotal > 21 && cantidadAses > 0) {
            valorTotal -= 10;
            cantidadAses--;
        }

        return valorTotal;
    }

    public int calculatePointsDealer() {
        List<String> mano = manoDealer.get(0);
        int valorTotal = 0;
        int cantidadAses = 0;

        for (String carta : mano) {
            String[] partes = carta.split("[♠♥♦♣ ]+");
            String valor = partes[0];

            if (Character.isDigit(valor.charAt(0))) {
                valorTotal += Integer.parseInt(valor);
            } else {
                switch (valor) {
                    case "J", "Q", "K" ->
                        valorTotal += 10;
                    case "A" -> {
                        valorTotal += 11;
                        cantidadAses++;
                    }
                    default ->
                        throw new IllegalArgumentException("Valor desconocido: " + valor);
                }
            }
        }

        // Ajusta el valor del As si supera 21
        while (valorTotal > 21 && cantidadAses > 0) {
            valorTotal -= 10;
            cantidadAses--;
        }
        return valorTotal;
    }

    // Permite al jugador dividir su mano si tiene dos cartas del mismo valor
    private void realizarSplit(int indiceJugador) {
        List<String> mano = manosJugadores.get(indiceJugador);
        if (mano.size() == 2) {
            String valor1 = mano.get(0).split("[♠♥♦♣ ]+")[0];
            String valor2 = mano.get(1).split("[♠♥♦♣ ]+")[0];
            if (valor1.equals(valor2)) {
                List<String> nuevaMano = new ArrayList<>();
                nuevaMano.add(mano.remove(1));
                manosJugadores.add(nuevaMano);

                System.out.println("Jugador " + (indiceJugador + 1) + " realizó un Split.");
                dealCardToPlayer(indiceJugador);
                dealCardToPlayer(manosJugadores.size() - 1);
                System.out.println("Nueva mano 1: " + manosJugadores.get(indiceJugador));
                System.out.println("Nueva mano 2: " + nuevaMano);
            } else {
                System.out.println("No se puede hacer Split: las cartas no son iguales.");
            }
        } else {
            System.out.println("El Split solo es posible con 2 cartas.");
        }
    }

    // Verifica si algún jugador ganó, perdió o si el juego terminó
    @Override
    public void checkGameOver() {
        // Turno del dealer
        dealCardToDealer();
        System.out.println("Dealer: " + manoDealer.get(0));
        System.out.println("Dealer tiene " + calculatePointsDealer() + " puntos");

        int puntosDealer = (calculatePointsDealer() <= 21) ? calculatePointsDealer() : 0;

        int win = -1;
        int maxPuntos = 0;
        int[] puntos = new int[totalJugadores];
        List<Integer> empatados = new ArrayList<>();

// Calcular puntos de jugadores y detectar máximo válido
        for (int i = 0; i < totalJugadores; i++) {
            puntos[i] = calculatePoints(i);
            int p = puntos[i];

            if (p <= 21 && p > puntosDealer) {
                if (p > maxPuntos) {
                    maxPuntos = p;
                    win = i;
                    empatados.clear();
                    empatados.add(i);
                } else if (p == maxPuntos) {
                    empatados.add(i);
                }
            }
        }

// Mostrar resultado
        if (empatados.isEmpty()) {
            if (puntosDealer == 0) {
                System.out.println("Todos los jugadores y el dealer se pasaron de 21. Nadie gana.");
            } else {
                System.out.println("El dealer gana con " + puntosDealer + " puntos.");
            }
        } else if (empatados.size() > 1) {
            System.out.print("Empate entre jugadores: ");
            for (int i = 0; i < empatados.size(); i++) {
                System.out.print((empatados.get(i) + 1));
                if (i < empatados.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(" con " + maxPuntos + " puntos" + (maxPuntos == 21 ? " y tienen BLACKJACK!" : "."));
        } else {
            System.out.println("Jugador " + (win + 1) + " gana con " + maxPuntos + " puntos"
                    + (maxPuntos == 21 ? " y tiene BLACKJACK!" : "."));
        }

// Mostrar perdedores
        for (int i = 0; i < totalJugadores; i++) {
            if (!empatados.contains(i)) {
                System.out.println("Jugador " + (i + 1) + " pierde con " + puntos[i] + " puntos"
                        + (puntos[i] > 21 ? " (se pasó de 21)." : "."));
            }
        }

// Mostrar resultado del dealer
        if (puntosDealer <= 21) {
            System.out.println("Dealer termina con " + puntosDealer + " puntos.");
        } else {
            System.out.println("Dealer se pasó de 21.");
        }

    }
}
