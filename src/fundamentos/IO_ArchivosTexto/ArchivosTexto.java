
package fundamentos.IO_ArchivosTexto;


import java.util.Scanner;

public class ArchivosTexto {

   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Metodos metodos = new Metodos();

        System.out.println("---------------------------BIENVENIDO REGISTRO DE DATOS---------------------------");
        while (true) {
            // Nombre
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine().trim();

            if (nombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                continue;
            }

            // Apellido
            System.out.print("Apellido: ");
            String apellido = scanner.nextLine().trim();
            if (apellido.isEmpty()) {
                System.out.println("El apellido no puede estar vacío.");
                continue;
            }

            // Edad
            String edad;
            while (true) {
                System.out.print("Edad: ");
                edad = scanner.nextLine().trim();
                if (edad.matches("\\d+")) break;
                System.out.println("Ingrese un número válido para la edad.");
            }

            // Ciudad
            System.out.print("Ciudad: ");
            String ciudad = scanner.nextLine().trim();
            if (ciudad.isEmpty()) ciudad = "-";

            // País
            System.out.print("Pais: ");
            String pais = scanner.nextLine().trim();
            if (pais.isEmpty()) pais = "-";

            // Teléfono
            String telefono;
            while (true) {
                System.out.print("Telefono: ");
                telefono = scanner.nextLine().trim();
                if (telefono.matches("\\+?\\d+")) break;
                System.out.println("Ingrese un teléfono válido (solo números y opcional +).");
            }

            // Email
            String email;
            while (true) {
                System.out.print("Email: ");
                email = scanner.nextLine().trim();
                if (email.contains("@")) break;
                System.out.println("Ingrese un email válido.");
            }

            // Confirmar guardado
            System.out.print("\n¿Desea guardar este registro? (Si/No): ");
            String opcionGuardar = scanner.nextLine().trim();
            if (opcionGuardar.equalsIgnoreCase("Si")) {
                metodos.cargarDatosRegistro(nombre, apellido, edad, ciudad, pais, telefono, email);
                metodos.registrarDatosArchivo();
            } else {
                System.out.println("Registro no guardado.");
            }

            // Preguntar si desea ingresar otro registro
            System.out.print("\n¿Desea ingresar otro registro? (Si/No): ");
            String opcionContinuar = scanner.nextLine().trim();
            if (opcionContinuar.equalsIgnoreCase("No")) {
                System.out.println("Saliendo del programa...");
                break;
            }
            System.out.println("------------------------------------------------------------------------------------");
        }

        scanner.close();
    }
}