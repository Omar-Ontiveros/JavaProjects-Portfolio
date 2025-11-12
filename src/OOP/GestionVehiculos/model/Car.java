package OOP.GestionVehiculos.model;

public class Car extends Vehicle {

    private String body;
    private int numberOfDoors;
    private int numberOfSeats;
    private boolean hasSunroof;

    public Car() {

    }

    public Car(int id, String brand, String model, String color, String engineType,
            int year, int serialNumber, int fuelCapacity,
            String body, int numberOfDoors, boolean hasSunroof, int numberOfSeats) {

        super(id, brand, model, color, engineType, year, serialNumber, fuelCapacity);
        this.body = body;
        this.numberOfDoors = numberOfDoors;
        this.numberOfSeats = numberOfSeats;
        this.hasSunroof = hasSunroof;
    }

    public int getIdCar() {
        return getId();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "body : %s%nnumberOfDoors : %d%nnumberOfSeats : %d%nhasSunroof : %b%n",
                body, numberOfDoors, numberOfSeats, hasSunroof
        );
    }

    // Métodos propios de Car
    public void openTrunk() {
        System.out.println("Trunk is now open.");
    }

    public void closeTrunk() {
        System.out.println("Trunk is now closed.");
    }

    public void turnOnAirConditioning() {
        System.out.println("Air conditioning turned on.");
    }

    @Override
    public void start() {
        System.out.println("Car started.");
    }

    @Override
    public void stop() {
        System.out.println("Car stopped.");
    }

    @Override
    public void displayDetails() {
        System.out.println(this);
    }

    @Override
    public void refuel() {
        System.out.println("Car refueled.");
    }

    @Override
    public void service() {
        System.out.println("Car serviced.");
    }
}
