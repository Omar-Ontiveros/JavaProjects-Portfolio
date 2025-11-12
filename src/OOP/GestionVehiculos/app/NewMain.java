package OOP.GestionVehiculos.app;

import OOP.GestionVehiculos.model.Car;
import OOP.GestionVehiculos.model.Motorcycle;
import OOP.GestionVehiculos.model.Trunck;
import OOP.GestionVehiculos.service.VehicleManager;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class NewMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleManager manager = new VehicleManager();
        int option;
        test(manager);

        do {
            System.out.println("\n=== VEHICLE SYSTEM ===");
            System.out.println("1. Manage Car");
            System.out.println("2. Manage Truck");
            System.out.println("3. Manage Motorcycle");
            System.out.println("0. Exit");
            option = scanner.nextInt();

            switch (option) {
                case 1 ->
                    manageCarType(manager, scanner, "Car");
                case 2 ->
                    manageCarType(manager, scanner, "Truck");
                case 3 ->
                    manageCarType(manager, scanner, "Motorcycle");
                case 0 ->
                    System.out.println("Gestión finalizada");
                default ->
                    System.out.println("Opción incorrecta");
            }
        } while (option != 0);

        scanner.close();
    }

    private static void manageCarType(VehicleManager manager, Scanner scanner, String type) {

        scanner.nextLine();
        String brand, model, color, engineType;
        int year, serialNumber, fuelCapacity;
        String option = "";

        while (!option.equalsIgnoreCase("salir") && !option.equals("5")) {

            System.out.printf("%n1. Agregar%n2. Eliminar%n3. Buscar%n4. Listar%n5. Salir%n");
            option = scanner.nextLine();

            switch (option.toLowerCase()) {
                case "1", "agregar" -> {
                    switch (type.toLowerCase()) {
                        case "car" -> {
                            System.out.print("Enter brand: ");
                            brand = scanner.nextLine();

                            System.out.print("Enter model: ");
                            model = scanner.nextLine();

                            System.out.print("Enter color: ");
                            color = scanner.nextLine();

                            System.out.print("Enter engine type: ");
                            engineType = scanner.nextLine();

                            System.out.print("Enter year: ");
                            year = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter serial number: ");
                            serialNumber = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter fuel capacity: ");
                            fuelCapacity = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter body type: ");
                            String body = scanner.nextLine();

                            System.out.print("Enter number of doors: ");
                            int numberOfDoors = Integer.parseInt(scanner.nextLine());

                            System.out.print("Has sunroof (true/false): ");
                            boolean hasSunroof = Boolean.parseBoolean(scanner.nextLine());

                            System.out.print("Enter number of seats: ");
                            int numberOfSeats = Integer.parseInt(scanner.nextLine());

                            int id = 4;
                            //int id = validateId(manager);
                            Car car = new Car(id, brand, model, color, engineType, year, serialNumber, fuelCapacity, body, numberOfDoors, hasSunroof, numberOfSeats);
                            manager.addVehicle(car);
                        }

                        case "truck" -> {
                            System.out.print("Enter brand: ");
                            brand = scanner.nextLine();

                            System.out.print("Enter model: ");
                            model = scanner.nextLine();

                            System.out.print("Enter color: ");
                            color = scanner.nextLine();

                            System.out.print("Enter engine type: ");
                            engineType = scanner.nextLine();

                            System.out.print("Enter year: ");
                            year = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter serial number: ");
                            serialNumber = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter fuel capacity: ");
                            fuelCapacity = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter trailer attached (yes/no): ");
                            String trailerAttached = scanner.nextLine();

                            System.out.print("Enter load type: ");
                            String loadType = scanner.nextLine();

                            System.out.print("Enter cargo capacity: ");
                            int cargoCapacity = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter number of axles: ");
                            int numberOfAxles = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter tire count: ");
                            int tireCount = Integer.parseInt(scanner.nextLine());

                            int id = validateId(manager);
                            Trunck trunck = new Trunck(id, brand, model, color, engineType, year, serialNumber, fuelCapacity, cargoCapacity, numberOfAxles, tireCount, trailerAttached, loadType);
                            manager.addVehicle(trunck);
                        }

                        case "motorcycle" -> {
                            System.out.print("Enter brand: ");
                            brand = scanner.nextLine();

                            System.out.print("Enter model: ");
                            model = scanner.nextLine();

                            System.out.print("Enter color: ");
                            color = scanner.nextLine();

                            System.out.print("Enter engine type: ");
                            engineType = scanner.nextLine();

                            System.out.print("Enter year: ");
                            year = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter serial number: ");
                            serialNumber = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter fuel capacity: ");
                            fuelCapacity = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter handlebar type: ");
                            String handlebarType = scanner.nextLine();

                            System.out.print("Enter wheel type: ");
                            String wheelType = scanner.nextLine();

                            System.out.print("Enter engine displacement: ");
                            int engineDisplacement = Integer.parseInt(scanner.nextLine());

                            System.out.print("Enter saddle height: ");
                            int saddleHeight = Integer.parseInt(scanner.nextLine());

                            System.out.print("Does it have a sidecar (true/false)? ");
                            boolean hasSidecar = Boolean.parseBoolean(scanner.nextLine());

                            int id = validateId(manager);
                            Motorcycle motorcycle = new Motorcycle(id, brand, model, color, engineType, year, serialNumber, fuelCapacity, handlebarType, wheelType, engineDisplacement, saddleHeight, hasSidecar);
                            manager.addVehicle(motorcycle);
                        }
                    }
                }
                case "2", "eliminar" -> {
                    System.out.print("Ingrese ID del auto a eliminar: ");
                    int id = scanner.nextInt();
                    switch (type.toLowerCase()) {
                        case "car" -> {
                            manager.removeVehicle(id, Car.class);
                        }
                        case "trunck" -> {
                            manager.removeVehicle(id, Trunck.class);
                        }
                        case "motorcycle" -> {
                            manager.removeVehicle(id, Motorcycle.class);
                        }

                    }
                    scanner.nextLine();
                }

                case "3", "buscar" -> {
                    System.out.print("Ingrese ID del auto a buscar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    switch (type.toLowerCase()) {
                        case "car" -> {
                            manager.foundVehicle(id, Car.class);
                        }
                        case "trunck" -> {
                            manager.foundVehicle(id, Trunck.class);
                        }
                        case "motorcycle" -> {
                            manager.foundVehicle(id, Motorcycle.class);
                        }

                    }

                }
                case "4", "listar" ->
                    manager.listVehicles(Car.class);

                case "5", "salir" ->
                    System.out.println(
                            "Saliendo del menú de autos...");
                default ->
                    System.out.println(
                            "Opción incorrecta");
            }
        }
    }

    //Metod para verificar si el id existe en la lita
    private static int validateId(VehicleManager manager) {
        int id = UUID.randomUUID().hashCode();
        while (manager.vehicleExists(id)) {
            id = UUID.randomUUID().hashCode();
        }
        return id;
    }

    //Metodo para precargar valore para testear el completo funcionamiento con distintos tipos de Vehiculos 
    private static void test(VehicleManager manager) {

// Listas de objetos
        List<Car> carList = List.of(
                new Car(1, "Toyota", "Corolla", "Blue", "Gasoline", 2022, 20304, 40, "Sedan", 4, false, 6),
                new Car(2, "Honda", "Civic", "Red", "Gasoline", 2021, 20305, 42, "Sedan", 4, true, 5),
                new Car(3, "Ford", "Focus", "White", "Gasoline", 2020, 20306, 38, "Hatchback", 4, false, 5),
                new Car(4, "BMW", "320i", "Black", "Gasoline", 2022, 20307, 45, "Sedan", 4, true, 5),
                new Car(5, "Audi", "A4", "Silver", "Gasoline", 2023, 20308, 50, "Sedan", 4, true, 5)
        );

        List<Trunck> truckList = List.of(
                new Trunck(6, "Volvo", "FH", "White", "Diesel", 2021, 20309, 300, 15000, 3, 18, "Yes", "General Cargo"),
                new Trunck(7, "Scania", "R500", "Blue", "Diesel", 2020, 20310, 280, 14000, 3, 16, "Yes", "Liquid"),
                new Trunck(8, "Mercedes", "Actros", "Red", "Diesel", 2022, 20311, 320, 16000, 4, 20, "No", "Construction Materials"),
                new Trunck(9, "MAN", "TGX", "Green", "Diesel", 2023, 20312, 310, 15500, 3, 18, "Yes", "Refrigerated"),
                new Trunck(10, "DAF", "XF", "Black", "Diesel", 2021, 20313, 290, 14500, 3, 16, "No", "General Cargo")
        );

        List<Motorcycle> motoList = List.of(
                new Motorcycle(11, "Yamaha", "R1", "Blue", "Gasoline", 2022, 20314, 20, "Sport", "Alloy", 1000, 820, false),
                new Motorcycle(12, "Honda", "CBR600", "Red", "Gasoline", 2021, 20315, 18, "Sport", "Alloy", 600, 810, false),
                new Motorcycle(13, "Kawasaki", "Ninja", "Green", "Gasoline", 2023, 20316, 19, "Sport", "Alloy", 650, 800, false),
                new Motorcycle(14, "Suzuki", "GSX-R", "White", "Gasoline", 2022, 20317, 21, "Sport", "Alloy", 750, 805, false),
                new Motorcycle(15, "Ducati", "Panigale", "Red", "Gasoline", 2023, 20318, 22, "Sport", "Alloy", 1100, 825, false)
        );

// Recorrer y agregar con for
        for (Car car : carList) {
            manager.addVehicle(car);
        }

        for (Trunck truck : truckList) {
            manager.addVehicle(truck);
        }

        for (Motorcycle moto : motoList) {
            manager.addVehicle(moto);
        }

    }

}
