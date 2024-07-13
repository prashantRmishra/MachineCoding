
abstract class Vehicle{
    public int calculateValue();
}

// The above class follows solid principle but below does not 
//what if we add another vehilce class Bus then we will have to modify the calculateValue() methed below

/*
class Vehicle{
    public int calculateValue(Vehicle c){
        if(c instanceOf(Car)){
            return 34;
        }
        else if (c instanceOf(Truck)){
            return 45;
        }
    }
}
*/