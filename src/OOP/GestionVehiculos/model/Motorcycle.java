package OOP.GestionVehiculos.model;

public class Motorcycle extends Vehicle {

    private String handlebarType, wheelType;
    private int engineDisplacement, saddleHeight;
    private boolean hasSidecar;

    public Motorcycle() {
    }

    public Motorcycle(int id, String brad, String model, String color, String engineType, int year, int serialNumber,
            int fuelCapacity, String handlebarType, String wheelType, int engineDisplacement, int saddleHeight,
            boolean hasSidecar) {
        super(id, brad, model, color, engineType, year, serialNumber, fuelCapacity);

    }

    public int getIdMortocycle() {
        return getId();
    }

    public void doWheelie() {
        System.out.println("Doing a wheelie!");
    }

    public void kickStart() {
        System.out.println("Motorcycle kick-started.");
    }

    public void turnOnHeadlights() {
        System.out.println("Headlights turned on.");
    }

    @Override
    public void start() {
        System.out.println("Motorcycle started.");
    }

    @Override
    public void stop() {
        System.out.println("Motorcycle stopped.");
    }

    @Override
    public void displayDetails() {
        System.out.println(this);
    }

    @Override
    public void refuel() {
        System.out.println("Motorcycle refueled.");
    }

    @Override
    public void service() {
        System.out.println("Motorcycle serviced.");
    }
}
