
## Liskov Substitution Principle

**Objects should be replaceable with their subtype without affecting the correctness of the code**

Lets understand this with inheritance(Is-a relationship)
example: Ostrich is a bird, Hunchback is a car etc.

Lets understand this with example : 

Racing-car is a car

```java
public class Car{
    public double getCabinWidth(){
        //return cabin width
    }
}
```

```java
public class RacingCar extends Car{
    @Override
    public double getCabinWidth(){
        //UNIMPLEMENTED
    }
    public double getCockpitWidth(){
        //return cockpit width of the racing car
    }
}
```
`RacingCar` overrides the `getCabinWidth()` of the car class but leaves it **unimplemented** because Racing cars don't have cabin width( if you see a formula 1 racing car, it does not have any interior space, all it has is just a cockpit where the driver sits)
Hence the interior space in racing car is called cockpit.
Note: Racing cars have some specifications that might not match the generic car

```java
public class CarUtil{
    Car car1 = new Car();
    Car car2 = new Car();
    Car car3 = new RacingCar();

    List<Car> myCars = new ArrayList<>();
    myCars.add(car1);
    myCars.add(car2);
    myCars.add(car3);
    // this will not work in 3rd iteration, because the getCabinWidth() in RacingCar is not implemented 
    for(Car car  : myCars){
        System.out.println(car.getCabinWidth());
    }
}
```
This is a design that has been exposed, as the for loop will for the the 3rd iteration.
To fix this we have to strike at the root which is Inheritance itself.

---

Solution 1 : (**Breaking the Hierarchy**)
We have to break the inheritance, instead we will come up with a common parent for both `Car` and `RacingCar`

We will create a very generic parent class called  `Vehicle`

```java
public class Vehicle{
    public double getInteriorWidth(){
        //return the interior width
    }
}
```
```java
public class Car extends Vehicle{
    @Override
    public double getInteriorWidth(){
        return this.getCabinWidth();
    }
    public double getCabinWidth(){
        //return cabin width
    }
}
```
```java
public class RacingCar extends Vehicle{
    @Override
    public double getInteriorWidth(){
        return this.getCockpitWidth();
    }

    public double getCockpitWidth(){
        //return cockpit width of the racing car
    }
}
```
```java
public class VehicleUtils{
    Vehicle vehicle1 = new Car();
    Vehicle vehicle2 = new Car();
    Vehicle vehicle2 = new RacingCar();

    List<Vehicle> vehicles = new ArrayList<>();
    vehicles.add(vehicle1);
    vehicles.add(vehicle2);
    vehicles.add(vehicle3);
    for(Vehicle vehicle : vehicles){
        System.out.println(vehicle.getInteriorWidth());
    } 
}
```
***Breaking the hierarchy**: Break the hierarchy if the substitution fails*

---

Solution 2: **Tell don't ask**

Let's take another example of amazon
Amazon offers x amount of discount on all the third party products.
And offers 1.5 times of x on all its in-house products (*Amazon Basics* products are amazon in-house products)

```java
public class Product{
    public double discount = 20;//x amount of discount on all the third party products on amazon
    public double getDiscount(){
        return discount;
    }
}
```
```java
public class InHouseProduct extends Product{
    public void applyDiscount(){
        discount  = discount*1.5;// 1.5 times more discount on InHouseProducts
    }
}
```
```java
public class PricingUtils{
    Product p1 = new Product();
    Product p2 = new Product();
    Product p2 = new InHouseProduct();
    List<Product> products = new ArrayList<>();
    products.add(p1);
    products.add(p2);
    products.add(p2);
    for(Product product : products){
        if(product instanceOf InHouseProduct){
            ((InHouseProduct)product).applyDiscount();
        }
        System.out.println(product.getDiscount());
    }
}
```
Note that the `if` statement is involved to update the discount amount of `InHouseProduct` which is **against the Liskov substitution principle** ( as we should have been able to replace the object `Product` with its subtype `InHouseProduct`), but here in if statement we are manually updating the discount amount which should not be done.

Slight modification in the `InHouseProduct` class will fix this issue


```java
public class InHouseProduct extends Product{

    @Override
    public double getDiscount(){
        applyDiscount();
        return discount;
    }
    public void applyDiscount(){
        discount  = discount*1.5;
    }
}
```

And finally removing the if statement from the `PricingUtils` class

```java
public class PricingUtils{
    Product p1 = new Product();
    Product p2 = new Product();
    Product p2 = new InHouseProduct();
    List<Product> products = new ArrayList<>();
    products.add(p1);
    products.add(p2);
    products.add(p2);
    for(Product product : products){
        System.out.println(product.getDiscount());
    }
}
```

***Tell don't ask***: Here we are telling utils class to print all the discount and the utils class does not have to ask anything ( as it was asking by if statement earlier)

---
