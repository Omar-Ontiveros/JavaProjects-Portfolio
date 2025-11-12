package fundamentos.Arrays_Listas_Contactos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Metodos {
    Map<String, Object> contactos = new HashMap<>();
    String nombre,
            telefono,
            email,
            direccion,
            dato;

    public void procesarAgregar(String nombre, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public void procesarDato(String dato) {
        this.dato = dato;
    }

    public void opcion(String eleccion) {
        switch (eleccion) {
            case "1", "agregar contacto", "add", "agregar" -> {
                agregarContacto();
            }
            case "2", "mostrar contactos", "mostrar", "show" ->
                mostrarContactos();

            case "3", "buscar contacto", "buscar", "search" ->
                buscarContacto();

            case "4", "eliminar contacto", "eliminar", "delete" ->
                eliminarContacto();

            default ->
                System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
        }
    }

    private Map<String, Object> agregarContacto() {
        // Lógica para agregar un contacto
        ArrayList<String> telefonos = new ArrayList<>(List.of(telefono.split(",")));
        ArrayList<String> emails = new ArrayList<>(List.of(email.split(",")));

        Datos datos = new Datos(telefonos, emails, direccion);
        contactos.put(nombre, datos);
        return contactos;
    }

    private void mostrarContactos() {
        // Lógica para mostrar contactos
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos guardados.");
        } else {
            System.out.println("Lista de contactos:");
            contactos.forEach((nombre, datos) -> {
                Datos d = (Datos) datos; // convertir Object → Datos
                System.out.printf("\n----------------------%nContacto encontrado:%nNombre: %s%nTelefonos: %s%nEmails: %s%nDirección: %s%n%n----------------------%n%n",
                nombre, d.telefonos, d.emails, d.direccion);
            });
        }

    }

    private void buscarContacto() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos guardados.");
            return;
        }
        boolean encontrado = false;
        for (Map.Entry<String, Object> entry : contactos.entrySet()) {
            String nombre = entry.getKey();
            Datos d = (Datos) entry.getValue();

            if (nombre.equalsIgnoreCase(dato) ||
                    d.telefonos.contains(dato) ||
                    d.emails.contains(dato)) {
                System.out.printf("\n----------------------%nContacto encontrado:%nNombre: %s%nTelefonos: %s%nEmails: %s%nDirección: %s%n%n----------------------%n%n",
                nombre, d.telefonos, d.emails, d.direccion);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.printf("\n----------------------%nContacto no encontrado.%n----------------------%n%n");
        }
    }

    private void eliminarContacto() {
        // Lógica para eliminar un contacto
        if (contactos.isEmpty()) {
            System.out.printf("\n----------------------%nNo hay contactos guardados.%n----------------------%n%n");
        } else {
            if (contactos.containsKey(dato)) {
                contactos.remove(dato);
                System.out.printf("\n----------------------%nContacto eliminado correctamente.%n----------------------%n%n");

            } else {
                System.out.printf("\n----------------------%nContacto no encontrado.%n----------------------%n%n");
            }
        }
    }
}