package fundamentos.IO_ArchivosTexto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Metodos {
    
    private String nombre, apellido, edad, ciudad, pais, telefono, email;

    // Guardar los datos en variables de instancia
    public void cargarDatosRegistro(String nombre, String apellido, String edad, String ciudad, String pais, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.ciudad = ciudad;
        this.pais = pais;
        this.telefono = telefono;
        this.email = email;
    }

    // Método para guardar los datos en el archivo
    public void registrarDatosArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Registro.txt", true))) {
            String linea = String.format(
                "Nombre: %s%nApellido: %s%nEdad: %s%nCiudad: %s%nPais: %s%nTelefono: %s%nEmail: %s%n---------------------",
                nombre, apellido, edad, ciudad, pais, telefono, email
            );

            writer.write(linea);
            writer.newLine();
            writer.flush();

            System.out.println("Registro guardado exitosamente en notas.txt!");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
}
