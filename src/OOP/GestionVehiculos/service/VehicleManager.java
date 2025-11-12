package OOP.GestionVehiculos.service;

import java.util.HashMap;
import java.util.Map;

import OOP.GestionVehiculos.model.Vehicle;

public class VehicleManager {

    private final Map<Integer, Vehicle> vehicles = new HashMap<>();

    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getId(), vehicle);
    }

    public <T extends Vehicle> void removeVehicle(int id, Class<T> typeClass) {
        vehicles.entrySet().removeIf(entry -> typeClass.isInstance(entry.getValue())
                && entry.getKey() == id);
    }

    public <T extends Vehicle> void foundVehicle(int id, Class<T> typeClass) {
        for (Map.Entry<Integer, Vehicle> en : vehicles.entrySet()) {
            if (en.getKey() == id && typeClass.isInstance(en.getValue())) {
                System.out.println(en.getValue());
            }
        }

    }

    public <T extends Vehicle> void listVehicles(Class<T> typeClass) {
        vehicles.values().stream()
                .filter(typeClass::isInstance)
                .forEach(System.out::println);
    }

    public boolean vehicleExists(int id) {
        return vehicles.values().stream()
                .anyMatch(v -> v.getId() == id);
    }

}
