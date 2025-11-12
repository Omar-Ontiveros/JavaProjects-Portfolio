package OOP.JuegoCartas.games;

import OOP.JuegoCartas.interfaces.GameActions;
import java.util.*;

public class PokerGame implements GameActions {

    // --- ATRIBUTOS PRINCIPALES ---
    private final List<String> baraja = new ArrayList<>();
    private final List<List<String>> manosJugadores = new ArrayList<>();
    private final Map<Integer, Map<Character, Integer>> fichasJugadores = new LinkedHashMap<>();
    private final Map<Character, Integer> fichasMesa = new LinkedHashMap<>();
    private final Map<Character, Integer> fichasParaSeguir = new LinkedHashMap<>();
    private final Map<Character, Integer> valorFichas = new HashMap<>();
    private int totalJugadores = 1;
    private int opcion = 0;

    // --- INICIALIZACIÓN GENERAL ---
    public void playersAndDeck(int cantidadJugadores) {
        this.totalJugadores = cantidadJugadores;
        inicializarFichasJugadores();
        inicializarMesa();
        shuffle();
    }

    private void inicializarFichasJugadores() {
        for (int i = 0; i < totalJugadores; i++) {
            Map<Character, Integer> fichas = new LinkedHashMap<>();
            fichas.put('B', 50);
            fichas.put('R', 50);
            fichas.put('A', 50);
            fichas.put('V', 40);
            fichas.put('N', 25);
            fichas.put('M', 6);
            fichas.put('Y', 3);
            fichasJugadores.put(i, fichas);
        }
    }

    private void inicializarMesa() {
        Random random = new Random();
        for (char c : new char[]{'B', 'R', 'A', 'V', 'N', 'M', 'Y'}) {
            fichasMesa.put(c, 0);
        }

        char colorInicial = random.nextBoolean() ? 'A' : 'R';
        int cantidadInicial = random.nextInt(1, 5);
        fichasMesa.put(colorInicial, cantidadInicial);

        valorFichas.put('B', 1);
        valorFichas.put('R', 5);
        valorFichas.put('A', 10);
        valorFichas.put('V', 25);
        valorFichas.put('N', 100);
        valorFichas.put('M', 500);
        valorFichas.put('Y', 1000);
    }

    // --- BARAJAR Y REPARTIR ---
    @Override
    public void shuffle() {
        String[] palos = {"♠", "♥", "♦", "♣"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        baraja.clear();

        for (String palo : palos) {
            for (String valor : valores) {
                baraja.add(valor + " " + palo);
            }
        }

        Collections.shuffle(baraja);
        dealCards();
    }

    @Override
    public void dealCards() {
        manosJugadores.clear();
        for (int i = 0; i < totalJugadores; i++) {
            List<String> mano = new ArrayList<>(baraja.subList(0, 2));
            baraja.subList(0, 2).clear();
            manosJugadores.add(mano);
        }
        showCards();
    }

    @Override
    public void showCards() {
        System.out.println("\nCartas de los jugadores:");
        for (int i = 0; i < totalJugadores; i++) {
            System.out.print("Jugador " + (i + 1) + ": " + manosJugadores.get(i));
            System.out.println(" | Fichas: " + fichasJugadores.get(i));
        }

        System.out.println("\n--- Fichas en la mesa ---");
        System.out.println(fichasMesa);
        playerAction();
    }

    // --- APUESTAS ---
    public void apostar(int jugador, char color, int cantidad) {
        Map<Character, Integer> fichasJugador = fichasJugadores.get(jugador);

        if (fichasJugador.get(color) >= cantidad) {
            fichasJugador.put(color, fichasJugador.get(color) - cantidad);
            fichasMesa.put(color, fichasMesa.get(color) + cantidad);
            System.out.println("Jugador " + (jugador + 1) + " apostó " + cantidad + " de " + color);
        } else {
            System.out.println("⚠️ No tienes suficientes fichas de color " + color);
        }
    }

    public boolean calcularApuesta(Map<Character, Integer> apuesta) {
        int totalMesa = fichasMesa.entrySet().stream()
                .mapToInt(e -> valorFichas.get(e.getKey()) * e.getValue()).sum();

        int totalApuesta = apuesta.entrySet().stream()
                .mapToInt(e -> valorFichas.get(e.getKey()) * e.getValue()).sum();

        return totalApuesta >= totalMesa;
    }

    // --- ACCIONES DEL JUGADOR ---
    @Override
    public void playerAction() {
        Scanner sc = new Scanner(System.in);
        fichasParaSeguir.clear();
        fichasParaSeguir.putAll(fichasMesa);
        List<Integer> jugadoresFuera = new ArrayList<>();

        for (int ronda = 1; ronda <= 4; ronda++) {
            System.out.println("\n=== RONDA " + ronda
                    + ((ronda == 1) ? " :Preflop" : (ronda == 2) ? ": Flop" : (ronda == 3) ? " :Turn" : " :River") + " ===");

            for (int i = 0; i < totalJugadores; i++) {
                if (jugadoresFuera.contains(i)) {
                    continue;
                }

                boolean turnoActivo = true;
                while (turnoActivo) {
                    System.out.println("\nJugador " + (i + 1) + ", elige una opción:");
                    System.out.println("1. Seguir\n2. Apostar\n3. Retirarse\n4. Salir");
                    opcion = sc.nextInt();

                    switch (opcion) {
                        case 1 -> { // SEGUIR
                            boolean puedeSeguir = true;
                            for (var entry : fichasParaSeguir.entrySet()) {
                                char color = entry.getKey();
                                int cantidad = entry.getValue();
                                if (cantidad > 0 && fichasJugadores.get(i).get(color) < cantidad) {
                                    puedeSeguir = false;
                                    break;
                                }
                            }

                            if (puedeSeguir) {
                                for (var entry : fichasParaSeguir.entrySet()) {
                                    if (entry.getValue() > 0) {
                                        apostar(i, entry.getKey(), entry.getValue());
                                    }
                                }
                                System.out.println("Jugador " + (i + 1) + " iguala la apuesta. Fichas actuales: " + fichasJugadores.get(i));
                            } else {
                                System.out.println("⚠️ No puedes seguir: no tienes suficientes fichas para igualar la mesa.");
                            }
                            turnoActivo = false;
                        }

                        case 2 -> { // APOSTAR
                            Map<Character, Integer> apuesta = new HashMap<>();
                            boolean apuestaValida = false;

                            while (!apuestaValida) {
                                String continuarApostando = "";
                                while (!continuarApostando.equalsIgnoreCase("salir")) {
                                    System.out.print("Color de ficha (B,R,A,V,N,M,Y): ");
                                    char color = sc.next().toUpperCase().charAt(0);
                                    System.out.print("Cantidad a apostar: ");
                                    int cant = sc.nextInt();
                                    sc.nextLine();
                                    if (fichasJugadores.get(i).getOrDefault(color, 0) >= cant) {
                                        apuesta.put(color, cant);
                                        System.out.println("Tu apuesta actual: " + apuesta);
                                        System.out.println("Pulsa Enter para añadir más o escribe 'salir' para confirmar.");
                                        continuarApostando = sc.nextLine();
                                    } else {
                                        System.out.println("⚠️ No Puedes apostar mas fichas del que dipones que son : " + color + " " + fichasJugadores.get(i).getOrDefault(color, 0));
                                    }
                                }

                                if (calcularApuesta(apuesta)) {
                                    for (var entry : apuesta.entrySet()) {
                                        apostar(i, entry.getKey(), entry.getValue());
                                    }
                                    fichasParaSeguir.clear();
                                    fichasParaSeguir.putAll(apuesta);
                                    apuestaValida = true;
                                    turnoActivo = false;
                                } else {
                                    apuesta.clear();
                                    System.out.println("⚠️ Tu apuesta es menor que la mesa. Vuelve a intentarlo.");
                                }
                            }
                        }

                        case 3 -> {
                            System.out.println("Jugador " + (i + 1) + " se retira.");
                            jugadoresFuera.add(i);
                            turnoActivo = false;
                        }

                        case 4 -> {
                            System.out.println("Saliendo del juego...");
                            System.exit(0);
                        }

                        default ->
                            System.out.println("Opción no válida");
                    }
                }
            }
        }
    }

    @Override
    public void checkGameOver() {
        // TODO: Implementar condiciones de fin de juego
    }
}
