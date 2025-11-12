package OOP.GestionVehiculos.model;

public class Trunck extends Vehicle {

    private int cargoCapacity, numberOfAxles, tireCount;
    private String trailerAttached, loadType;

    public Trunck() {
    }

    public Trunck(int id, String brad, String model, String color, String engineType, int year, int serialNumber,
            int fuelCapacity, int cargoCapacity, int numberOfAxles, int tireCount, String traileString,
            String loadType) {
        super(id, brad, model, color, engineType, year, serialNumber, fuelCapacity);
        this.cargoCapacity = cargoCapacity;
        this.numberOfAxles = numberOfAxles;
        this.tireCount = tireCount;
        this.trailerAttached = traileString;
        this.loadType = loadType;
    }

    public int getIdTrunck() {
        return getId();
    }

    public void openTrunk() {
        System.out.println("Trunk is now open.");
    }

    public void unloadCargo() {
        System.out.println("Cargo unloaded.");
    }

    public void attachTrailer() {
        System.out.println("Trailer attached.");
    }

    @Override
    public void start() {
        System.out.println("Truck started.");
    }

    @Override
    public void stop() {
        System.out.println("Truck stopped.");
    }

    @Override
    public void displayDetails() {
        System.out.println(this);
    }

    @Override
    public void refuel() {
        System.out.println("Truck refueled.");
    }

    @Override
    public void service() {
        System.out.println("Truck serviced.");
    }

}
