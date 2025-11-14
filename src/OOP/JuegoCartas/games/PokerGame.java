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
    private final List<String> cartasMesa = new ArrayList<>();
    String[] palos = {"♠", "♥", "♦", "♣"};
    String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    private int totalJugadores = 1;
    private int opcion = 0;

    // Scanner como atributo de clase
    private final Scanner sc = new Scanner(System.in);

    // Colores válidos
    private static final char[] COLORES = {'B', 'R', 'A', 'V', 'N', 'M', 'Y'};

    // --- INICIALIZACIÓN GENERAL ---
    public void playersAndDeck(int cantidadJugadores) {
        this.totalJugadores = Math.max(1, cantidadJugadores);
        fichasJugadores.clear();
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
        fichasMesa.clear();

        for (char c : COLORES) {
            fichasMesa.put(c, 0);
        }

        char colorInicial = random.nextBoolean() ? 'A' : 'R';
        int cantidadInicial = random.nextInt(1, 5);
        fichasMesa.put(colorInicial, cantidadInicial);

        valorFichas.clear();
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
        cartasMesa.clear();

        for (int i = 0; i < totalJugadores; i++) {
            manosJugadores.add(tomarCartas(2));
        }

        cartasMesa.addAll(tomarCartas(3));
        playerAction();
    }

    private List<String> tomarCartas(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        if (baraja.size() < n) {
            throw new IllegalStateException("No hay suficientes cartas en la baraja para tomar " + n + " cartas.");
        }

        List<String> mano = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            mano.add(baraja.remove(0));
        }
        return mano;
    }

    @Override
    public void showCards() {
        // Método vacío opcional
    }

    public void showCards(int jugador, int ronda) {
        String mesa
                = (ronda == 1) ? "?, ?, ?, ?, ?"
                        : (ronda == 2) ? String.join(", ", cartasMesa) + ", ?, ?"
                                : (ronda == 3) ? String.join(", ", cartasMesa) + ", ?"
                                        : String.join(", ", cartasMesa);

        System.out.println("\nFichas en la mesa: " + fichasMesa);
        System.out.println("Cartas en mesa: " + mesa);

        System.out.println("\nCartas del jugador: " + (jugador + 1));
        System.out.println("Cartas: " + manosJugadores.get(jugador));
        System.out.println("Fichas: " + fichasJugadores.get(jugador));
    }

    // --- APUESTAS ---
    public void apostar(int jugador, char color, int cantidad) {
        if (!esColorValido(color)) {
            System.out.println("Color inválido: " + color);
            return;
        }

        Map<Character, Integer> fichasJugador = fichasJugadores.get(jugador);
        if (fichasJugador == null) {
            System.out.println("Jugador inválido: " + jugador);
            return;
        }

        int disponible = fichasJugador.getOrDefault(color, 0);
        if (disponible >= cantidad && cantidad > 0) {
            fichasJugador.put(color, disponible - cantidad);
            fichasMesa.put(color, fichasMesa.getOrDefault(color, 0) + cantidad);
        } else {
            System.out.println("⚠️ No tienes suficientes fichas de color " + color
                    + " (Disponibles: " + disponible + ")");
        }
    }

    public boolean calcularApuesta(Map<Character, Integer> apuesta) {
        int totalMesa = calcularValor(fichasMesa);
        int totalApuesta = calcularValor(apuesta);
        return totalApuesta >= totalMesa;
    }

    private int calcularValor(Map<Character, Integer> fichas) {
        if (fichas == null || fichas.isEmpty()) {
            return 0;
        }
        return fichas.entrySet().stream()
                .mapToInt(e -> valorFichas.getOrDefault(e.getKey(), 0) * e.getValue())
                .sum();
    }

    private boolean esColorValido(char c) {
        for (char cc : COLORES) {
            if (cc == c) {
                return true;
            }
        }
        return false;
    }

    // --- ACCIONES DEL JUGADOR ---
    @Override
    public void playerAction() {
        fichasParaSeguir.clear();
        fichasParaSeguir.putAll(fichasMesa);

        Set<Integer> jugadoresFuera = new HashSet<>();

        for (int ronda = 1; ronda <= 4; ronda++) {

            System.out.println(
                    "\n==================== RONDA " + ronda
                    + ((ronda == 1) ? " :Preflop"
                            : (ronda == 2) ? ": Flop"
                                    : (ronda == 3) ? " :Turn" : " :River")
                    + " ===================="
            );

            if (ronda >= 3) {
                cartasMesa.addAll(tomarCartas(1));
            }

            for (int i = 0; i < totalJugadores; i++) {

                showCards(i, ronda);

                if (jugadoresFuera.contains(i)) {
                    continue;
                }

                boolean turnoActivo = true;

                while (turnoActivo) {
                    System.out.println("\nJugador " + (i + 1) + ", elige una opción:");
                    System.out.println("1. Seguir\n2. Apostar\n3. Retirarse\n4. Salir");

                    if (!sc.hasNextInt()) {
                        System.out.println("Entrada inválida. Introduce un número.");
                        sc.next();
                        continue;
                    }

                    opcion = sc.nextInt();
                    sc.nextLine(); // limpiar buffer

                    switch (opcion) {

                        case 1 -> {
                            if (puedeSeguir(i)) {
                                igualarApuesta(i);
                                System.out.println("Jugador " + (i + 1)
                                        + " iguala la apuesta.\nFichas actuales: "
                                        + fichasJugadores.get(i));
                            } else {
                                System.out.println("⚠️ No puedes seguir: no tienes suficientes fichas.");
                            }
                            turnoActivo = false;
                        }

                        case 2 -> {
                            boolean apuestaValida = procesarApuestaInteractiva(i);
                            if (apuestaValida) {
                                turnoActivo = false;
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

                System.out.println("--------------------------------------------------------");
            }
        }
        checkGameOver();
    }

    private boolean puedeSeguir(int jugador) {
        Map<Character, Integer> fichasJugador = fichasJugadores.get(jugador);
        if (fichasJugador == null) {
            return false;
        }

        for (var entry : fichasParaSeguir.entrySet()) {
            char color = entry.getKey();
            int cantidad = entry.getValue();

            if (cantidad > 0 && fichasJugador.getOrDefault(color, 0) < cantidad) {
                return false;
            }
        }
        return true;
    }

    private void igualarApuesta(int jugador) {
        for (var entry : fichasParaSeguir.entrySet()) {
            if (entry.getValue() > 0) {
                apostar(jugador, entry.getKey(), entry.getValue());
            }
        }
    }

    private boolean procesarApuestaInteractiva(int jugador) {
        Map<Character, Integer> apuesta = new HashMap<>();

        while (true) {
            System.out.print("Color de ficha o 'salir' para confirmar: ");
            String token = sc.nextLine().trim();

            if (token.equalsIgnoreCase("salir")) {
                break;
            }
            if (token.isEmpty()) {
                continue;
            }

            char color = token.toUpperCase().charAt(0);

            if (!esColorValido(color)) {
                System.out.println("Color inválido.");
                continue;
            }

            System.out.print("Cantidad a apostar: ");
            if (!sc.hasNextInt()) {
                System.out.println("Cantidad inválida.");
                sc.next();
                continue;
            }

            int cant = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            int disponibles = fichasJugadores.get(jugador).getOrDefault(color, 0);

            if (disponibles >= cant && cant > 0) {
                apuesta.put(color, apuesta.getOrDefault(color, 0) + cant);
                System.out.println("Tu apuesta actual: " + apuesta);
            } else {
                System.out.println("⚠️ No tienes suficientes fichas.");
            }
        }

        if (apuesta.isEmpty()) {
            System.out.println("No hiciste ninguna apuesta.");
            return false;
        }

        if (calcularApuesta(apuesta)) {
            for (var entry : apuesta.entrySet()) {
                apostar(jugador, entry.getKey(), entry.getValue());
            }

            fichasParaSeguir.clear();
            fichasParaSeguir.putAll(apuesta);

            return true;
        }

        System.out.println("⚠️ Tu apuesta es menor que la mesa.");
        return false;
    }

    @Override
    public void checkGameOver() {
        for (int i = 0; i < totalJugadores; i++) {
            String resultado = evaluarMano(i);
            System.out.println("Jugador " + (i + 1) + ": " + resultado);
        }
    }

    // --- Detección de manos de póker ---
    public String evaluarMano(int jugador) {
        List<String> cartasJugador = new ArrayList<>(manosJugadores.get(jugador));
        cartasJugador.addAll(cartasMesa);

        Map<String, Integer> cuentaValores = new HashMap<>();
        Map<String, Integer> cuentaPalos = new HashMap<>();
        List<Integer> valoresNumericos = new ArrayList<>();

        Map<String, Integer> valorMapa = new HashMap<>();
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (int i = 0; i < valores.length; i++) {
            valorMapa.put(valores[i], i + 2);
        }

        for (String carta : cartasJugador) {
            String[] partes = carta.split(" ");
            String valor = partes[0];
            String palo = partes[1];

            cuentaValores.put(valor, cuentaValores.getOrDefault(valor, 0) + 1);
            cuentaPalos.put(palo, cuentaPalos.getOrDefault(palo, 0) + 1);
            valoresNumericos.add(valorMapa.get(valor));
        }

        Collections.sort(valoresNumericos);

        // Escalera real
        for (String palo : palos) {
            List<String> escaleraReal = Arrays.asList("10 " + palo, "J " + palo, "Q " + palo, "K " + palo, "A " + palo);
            if (cartasJugador.containsAll(escaleraReal)) {
                return "Escalera Real (" + palo + ")";
            }
        }

        // Escalera de color (Straight Flush)
        String straightFlush = detectarStraightFlush(cartasJugador);
        if (straightFlush != null) {
            return "Escalera de Color (" + straightFlush + ")";
        }

        if (cuentaValores.containsValue(4)) {
            return "Póker";
        }
        if (cuentaValores.containsValue(3) && cuentaValores.containsValue(2)) {
            return "Full House";
        }
        if (cuentaPalos.containsValue(5)) {
            return "Color (Flush)";
        }
        if (detectarStraight(valoresNumericos)) {
            return "Escalera (Straight)";
        }
        if (cuentaValores.containsValue(3)) {
            return "Trío";
        }

        int pares = 0;
        for (int count : cuentaValores.values()) {
            if (count == 2) {
                pares++;
            }
        }
        if (pares == 2) {
            return "Doble Par";
        }
        if (pares == 1) {
            return "Par";
        }

        int maxValor = Collections.max(valoresNumericos);
        for (Map.Entry<String, Integer> entry : valorMapa.entrySet()) {
            if (entry.getValue() == maxValor) {
                return "Carta Alta (" + entry.getKey() + ")";
            }
        }

        return "Indefinido";
    }

    private boolean detectarStraight(List<Integer> valores) {
        Set<Integer> set = new HashSet<>(valores);
        List<Integer> lista = new ArrayList<>(set);
        Collections.sort(lista);

        for (int i = 0; i <= lista.size() - 5; i++) {
            boolean consecutivas = true;
            for (int j = 0; j < 4; j++) {
                if (lista.get(i + j + 1) - lista.get(i + j) != 1) {
                    consecutivas = false;
                    break;
                }
            }
            if (consecutivas) {
                return true;
            }
        }

        // Caso A-2-3-4-5
        if (set.contains(14) && set.contains(2) && set.contains(3) && set.contains(4) && set.contains(5)) {
            return true;
        }

        return false;
    }

    private String detectarStraightFlush(List<String> cartasJugador) {
        Map<String, List<Integer>> porPalo = new HashMap<>();
        Map<String, Integer> valorMapa = new HashMap<>();
        String[] valores = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        for (int i = 0; i < valores.length; i++) {
            valorMapa.put(valores[i], i + 2);
        }

        for (String carta : cartasJugador) {
            String[] partes = carta.split(" ");
            String valor = partes[0];
            String palo = partes[1];
            porPalo.computeIfAbsent(palo, k -> new ArrayList<>()).add(valorMapa.get(valor));
        }

        for (Map.Entry<String, List<Integer>> entry : porPalo.entrySet()) {
            if (entry.getValue().size() >= 5 && detectarStraight(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
