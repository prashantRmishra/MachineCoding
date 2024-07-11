package Patterns.creational.singleton;

public class Singleton {
    // Private static variable to hold the single instance of the class
    private static volatile Singleton instance = null;

    // Private constructor to prevent instantiation
    private Singleton() {
        // Initialize any resources if needed
    }

    // Public method to provide access to the instance
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // Example method to demonstrate functionality
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }

    public static void main(String[] args) {
        // Get the single instance of the Singleton class
        Singleton singleton = Singleton.getInstance();
        
        // Call a method on the instance
        singleton.showMessage();
    }
}

/* 
 * Explanation
Private Static Variable:

private static volatile Singleton instance = null;
The volatile keyword ensures that multiple threads handle the instance variable correctly when it is being initialized.
Private Constructor:

private Singleton() { }
The constructor is private to prevent the instantiation of the class from outside.
Public Static Method:

public static Singleton getInstance() { }
This method provides the global point of access to the instance.
It uses double-checked locking to ensure thread safety and improve performance by reducing the overhead of synchronization after the instance is initialized.
Double-Checked Locking:

The first check (if (instance == null)) is outside the synchronized block to avoid unnecessary synchronization once the instance is initialized.
The second check (if (instance == null)) inside the synchronized block ensures that only one thread can initialize the instance.
Usage
You can call Singleton.getInstance() to get the single instance of the Singleton class.
The main method in this example demonstrates how to use the singleton instance and call its method.
This implementation ensures that the Singleton class is lazily initialized, thread-safe, and has only one instance throughout the application.
 * 
 * 
 * 
 */
