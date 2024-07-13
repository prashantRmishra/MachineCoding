class Main{
    public static void main(String ars[]){
        Vehicle car = new Car();
        Vehicle truck = new Truck();
        System.out.println(car.calculateValue());
        System.out.println(truck.calculateValue());
        /*
        similarly if we have to add another vehicle let say
        suv then we can directly create another class 'Suv' and we can make this 
        class extends the Vehicle class and override the calculateValue() method 
        this way we will extends the feature of Vehicle without modifying it
        */
    }
}