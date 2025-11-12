package OOP.GestionVehiculos.model;

import OOP.GestionVehiculos.interfaces.VehicleActions;

public abstract class Vehicle implements VehicleActions {

    String brad, model, color, engineType;
    int id, year, serialNumber, fuelCapacity;

    public Vehicle() {

    }

    public Vehicle(int id, String brad, String model, String color, String engineType, int year, int serialNumber,
            int fuelCapacity) {
        this.id = id;
        this.brad = brad;
        this.model = model;
        this.color = color;
        this.engineType = engineType;
        this.year = year;
        this.serialNumber = serialNumber;
        this.fuelCapacity = fuelCapacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrad() {
        return brad;
    }

    public void setBrad(String brad) {
        this.brad = brad;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    @Override
    public String toString() {
        return String.format(
                "=== Vehicle Data ===%n"
                + "ID : %d%n"
                + "brand : %s%n"
                + "model : %s%n"
                + "color : %s%n"
                + "engineType : %s%n"
                + "year : %d%n"
                + "serialNumber : %d%n"
                + "fuelCapacity : %d%n",
                id, brad, model, color, engineType, year, serialNumber, fuelCapacity
        );
    }
}
