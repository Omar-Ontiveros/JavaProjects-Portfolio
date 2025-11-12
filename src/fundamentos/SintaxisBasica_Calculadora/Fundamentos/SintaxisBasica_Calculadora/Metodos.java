
package fundamentos.SintaxisBasica_Calculadora.Fundamentos.SintaxisBasica_Calculadora;


public class Metodos {
    // metodo para sumar dos numeros
    public static double sumar(double a, double b) {
        return a + b;
    }

    // metodo para restar dos numeros
    public static double restar(double a, double b) {
        return a - b;
    }

    // metodo para multiplicar dos numeros
    public static double multiplicar(double a, double b) {
        return a * b;
    }

    // metodo para dividir dos numeros
    public static double dividir(double a, double b) {
        if (b == 0) {
         System.out.println("Error: Division por cero no es permitida.");
            return 0;
        }
        return a / b;
    }
}
