package fundamentos.Arrays_Listas_Contactos;
import java.util.ArrayList;

class Datos {
    ArrayList<String> telefonos;
    ArrayList<String> emails;
    String direccion;

    Datos(ArrayList<String> telefonos, ArrayList<String> emails, String direccion) {
        this.telefonos = telefonos;
        this.emails = emails;
        this.direccion = direccion;
    }
    @Override
    public String toString() {
        return "Teléfonos: " + telefonos + ", Emails: " + emails + ", Dirección: " + direccion;
    }

}
