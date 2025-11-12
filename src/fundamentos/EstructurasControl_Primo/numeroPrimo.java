package fundamentos.EstructurasControl_Primo;


import java.util.List;
import java.util.Map;

/**
 *
 * @author Omar
 */

import java.util.Scanner; // importamos la clase Scanner
public class numeroPrimo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // creamos un objeto Scanner para leer la entrada del usuario
        System.out.println("Ingresar una lista de numero o un solo numero ejemplo: 3,5,7,9 o 11");

        String input = scanner.nextLine(); // leemos la entrada del usuario como una cadena
        
        Metodos metodo = new Metodos();
        Map<String, List<Integer>> resultado = metodo.numeros(input);

        System.out.println("Primos: " + resultado.get("Primos"));
        System.out.println("Compuestos: " + resultado.get("Compuestos"));// llamamos al método para procesar los números
    }
    
}
