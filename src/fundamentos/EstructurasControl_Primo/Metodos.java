package fundamentos.EstructurasControl_Primo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Metodos {
    public static Map<String, List<Integer>> numeros(String input) {
        Map<String, List<Integer>> listaNumeros = new HashMap<>();
        listaNumeros.put("Primos", new ArrayList<>());
        listaNumeros.put("Compuestos", new ArrayList<>());

        Arrays.stream(input.split(","))          // convierte la cadena separada por comas en un stream
              .map(String::trim)                 // elimina espacios extra
              .mapToInt(Integer::parseInt)       // convierte cada string a entero
              .forEach(numero -> {               // recorre cada número
                  if (esPrimo(numero)) {
                      listaNumeros.get("Primos").add(numero);
                  } else {
                      listaNumeros.get("Compuestos").add(numero);
                  }
              });

        return listaNumeros;
    }
    private static boolean esPrimo(int numero) {
        if (numero <= 1) return false; // caso base
        return IntStream.rangeClosed(2, (int) Math.sqrt(numero))
                        .noneMatch(i -> numero % i == 0);
    }
}

