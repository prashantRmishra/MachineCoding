# Creational Desing Pattern

## Singleton:

Creates single instance of a class

```java
public class Singleton{
	//final for creating only once,
	// volatile so that the changes made to variable are immediately visible to all other threads
	//static to hold the single instance of the class
	private static final volatile Singleton instance = null;

	//private so that it can not be intialized 
	private Singleton(){}

	public static Singleton getInstanace(){
		if(instance ==null){// first null check to insure only one instance is created
			synchronized(Singleton.class){
				if(instance ==null){// second null check to insure only one process is allowed to create the instance of Singleton class
					instance = new Singleton();
				}
			}
		}
		return instance;
	}

	public void getMessage(){
		sysout("message form Singleton class");
	}
}

public class Main{
	psvm(s []){
		Singleton instance = Singleton.getInstance();
		instance.getMessage();
	}
}
```

## Prototype

Used when creating objects is very expensive like objects created after querying a large database
It uses clone() method to create a shallow copy of the object

```java
class Shape implements Clonable{
	private int id;
	private String shape;
	public Shape(int id ,String shape){this.id = id;this.shape = shape;}
	public getId(){return this.id;}
	public getShape(){return this.shape;}
	@Override
	public Object clone(){
		Object clone = null;
		try{
			clone = super.clone();
		}
		catch(Exception e){
			//error message print
		}
		return clone;
	}
}
class Cirlce extends Shape{
	public Circle(int id, String shape){super(id,shape);}
}
public class ShapeCache{
	private static Map<Integer,Shape> map = new HashMap<>();
	public static getShape(int id){
		return map.get(id);
	}
	public Shape createClone(Shape p){
		return (Shape) map.put(p.getId(), p);
	}
}
class Main{
	psvm(s[]){
		Shape p1 = new Cirlce(1,"cirlce");
		Shape p2 = new Cirlce(2,"circle2");
		ShapeCache.addShape(p1);
		//create a shallow clone of the Shape object p1
		Shape copyOfP1 = Shape.cache.getClone(1);
	}
}
```

## Factory


```java
enum BurgerType{
	Cheese,Delux,Vegan;
}

abstract class Burger{
	abstract void  prepare();
}

class CheeseBurger extends Burger{
	@Override
	public void prepare(){
		sysout("CheeseBurger");
	}
}
class VeganBurger extends Burger{...}
class DeluxBurger extends Burger{...}

public class BurgerFactor{
	public static Burger getBurger(BurgerType type){

		switch(type){
			case Cheese:return new CheeseBurger();
			case Delux:return new DeluxBurger();
			case Vegan:return new VeganBurger();
			default:throw new IllegalArugumentException("Unknow burger type");
		}
	}
}
public Main{
	psvm(s[]){
		Burger b1 = burgerFactory.getBurger(BurgerType.Cheese);
		Burger b2 = burgerFactory.getBurger(BurgerType.Delux);
	}
}
```

## Builder 

Used for creating objects that have lot of attributes/properties some are mandatory and some are optional
Basically when a lot of complexities are involved in creating an object then it is better to user Builder


```java
abstract class House{
	protected String foundation;
	protected String walls;
	protected String roof;
	protected String window;
	protected boolean pool;
	protected boolean garage;

	public void addFoundation(String foundation){this.foundation =foundation;}
	public void addWalls(String walls){this.walls = walls;}
	public void addRoof(String roof){this.roof= roof;}
	public void addWindow(String windows){this.windows = windows;}
	public abstract void addGarage();
	public abstract void addPool();

	//respective get methods for all these attributes
}
class SimpleHouse extends House{

	@Override
	public void addGarage(){this.garage = false;}
	@Override
	public void addPool(){this.pool  = false;}
}
class LuxuryHouse extends House{
	@Override
	public void addGarage(){this.garage = true;}
	@Override
	public void addPool(){this.pool  = true;}
}
public class HouseBuilder{
	House house;
	public HouseBuilder(House h){
		this.house = h; 
	}
	public House addFoundation(String foundation){
		h.addFoundation(foundation);
		return h;
	}
	public House addWalls(String walls){
		h.addWalls(walls);
		return h;
	}
	public House addRoof(String roof){
		h.addRoof(roof);
		return h;
	}
	public House addWindows(String window){
		h.addWindow(window);
		return h;
	}
	public House addGarage(){
		h.addGarage();
		return h;
	}
	public House addSwimingPool(){
		h.addPool();
		return h;
	}
	public House build(){
		return this.house;
	}
}
class Main{

	psvm(s[]){

		HouseBuilder builder = new HouseBuilder(new SimpleHouse());

		House house = builder.addFoundation("good foundation").addWalls("good walls").addRoof("great root").addWindows("4 windows").addGarage().addSwimingPool().build();
	}
}
```

## Abstract factory

```java
abstract class Button{abstract void render();}
class MacButton extends Button{public void render(){}}
class WindowsButton extends Button{public void render(){}}
interface ButtonFactory{public Button createButton();}
class MacButtonFactory implements ButtonFactory{
	public Button createButton(){
		return new MacButton();
	}
}
class WindowsButtonFactory implements ButtonFactory{
	public Button createButton(){
		return new WindowsButton();
	}
}

class Main{
	psvm(s[]){
		ButtonFactory factory = new MacButtonFactory();
		Button macButton = factory.createButton();
		macButton.render();
		ButtonFactory factory2 = new WindowsButtonFactory();
		Button windowsButton = factory2.createButton();
		windowsButton.render();
	}
}
```



