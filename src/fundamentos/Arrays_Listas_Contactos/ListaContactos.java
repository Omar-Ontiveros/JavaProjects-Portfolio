
package fundamentos.Arrays_Listas_Contactos;

import java.util.Scanner;

/**
 *
 * @author Lenovo
 */
public class ListaContactos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Metodos metodos = new Metodos();
            while (true) {
            System.out.printf("\n----------------------%n"+
                "Bienvenido a la lista de contactos%n" +
                "Seleccione una opción:%n" +
                "1. Agregar contacto%n" +
                "2. Mostrar contactos%n" +
                "3. Buscar contacto%n" +
                "4. Eliminar contacto%n" +
                "5. Salir%n"
            );

            System.out.print("Opción: ");
            String eleccion = scanner.nextLine().toLowerCase();
            System.out.println("----------------------");

            if(eleccion.equals("5") || eleccion.equals("salir") || eleccion.equals("exit")){
                System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                break;
            }   

            switch (eleccion) {
                case "1", "agregar contacto", "add", "agregar" -> {
                    System.out.println("Ingrese los detalles del contacto a agregar: ");
                    System.out.print("Nombre completo: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Número de teléfono (si tiene más de un número sepárelos por ','): ");
                    String telefono = scanner.nextLine();

                    System.out.print("Email (si tiene más de un email sepárelos por ','): ");
                    String email = scanner.nextLine();

                    System.out.print("Dirección: ");
                    String direccion = scanner.nextLine();  
                    
                    metodos.procesarAgregar(nombre, telefono, email, direccion);
                    metodos.opcion(eleccion);
                }
                    
                case "2", "mostrar contactos", "mostrar", "show" -> {
                    System.out.print("Nombre del contacto a mostrar: ");
                    metodos.opcion(eleccion);
                    
                }
                        
                case "3", "buscar contacto", "buscar", "search" -> {
                    System.out.print("Dato a buscar: ");
                    String datoBuscar = scanner.nextLine();
                    metodos.procesarDato(datoBuscar);
                    metodos.opcion(eleccion);
                }
                        
                case "4", "eliminar contacto", "eliminar", "delete" -> {
                    System.out.print("Nombre del contacto a eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    metodos.procesarDato(nombreEliminar);
                    metodos.opcion(eleccion);
                }
            }
        }
    }
}