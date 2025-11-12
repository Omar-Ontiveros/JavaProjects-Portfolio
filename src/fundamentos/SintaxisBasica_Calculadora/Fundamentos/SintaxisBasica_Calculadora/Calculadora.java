
package fundamentos.SintaxisBasica_Calculadora.Fundamentos.SintaxisBasica_Calculadora;

//importamos las librerias 
import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) { // metodo principal
        // creamos el objeto scanner para leer datos por consola
        Scanner scanner = new Scanner(System.in);
        

        // Pedimos al usuario que elija una operacion
        System.out.print("Seleccione una operacion: ");
        String operacion = scanner.nextLine(); // leemos la operacion
    
        // pedimos al usuario que ingrese el primer numero
        System.out.print("Ingrese el primer numero: ");
        double num1 = scanner.nextDouble(); // leemos el primer numero

        // pedimos al usuario que ingrese el segundo numero
        System.out.print("Ingrese el segundo numero: ");
        double num2 = scanner.nextDouble(); // leemos el segundo numero
        
        Metodos metodos = new Metodos(); // creamos un objeto de la clase Metodos
        switch(operacion.toLowerCase()) { // evaluamos la operacion
            case "sumar":
                System.out.println("Resultado: " + metodos.sumar(num1, num2)); // mostramos el resultado de la suma
                break;
            case "restar":
                System.out.println("Resultado: " + metodos.restar(num1, num2)); // mostramos el resultado de la resta
                break;
            case "multiplicar":
                System.out.println("Resultado: " + metodos.multiplicar(num1, num2)); // mostramos el resultado de la multiplicacion
                break;
            case "dividir":
                System.out.println("Resultado: " + metodos.dividir(num1, num2)); // mostramos el resultado de la division
                break;
            default:
                System.out.println("Operacion no valida."); // mensaje de operacion no valida
        }


        scanner.close(); // cerramos el scanner
    }
}

