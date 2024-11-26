package Patterns;

import java.util.ArrayList;
import java.util.List;

// Toy abstract class
abstract class Toy {
    abstract void create();
}

// Concrete Toy classes
class Train extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Train");
    }
}

class Car extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Car");
    }
}

class Bicycle extends Toy {
    @Override
    public void create() {
        System.out.println("Creating Bicycle");
    }
}

// Facade class
class ToyFacade {
    private List<Toy> toys;

    public ToyFacade() {
        toys = new ArrayList<>();
        // Initialize with existing toys, you can add more toys easily without modifying the facade class
        toys.add(new Train());
        toys.add(new Car());
        toys.add(new Bicycle());
    }

    public void createAllToys() {
        for (Toy toy : toys) {
            toy.create();
        }
    }

    public void createToy(Class<? extends Toy> toyClass) {
        for (Toy toy : toys) {
            if (toy.getClass().equals(toyClass)) {
                toy.create();
                return;
            }
        }
        System.out.println("Toy not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Facade instance to handle all toys
        ToyFacade facade = new ToyFacade();
        
        // Create all toys
        facade.createAllToys();
        
        // Create a specific toy
        facade.createToy(Train.class);
        facade.createToy(Car.class);
        facade.createToy(Bicycle.class);
    }
}
