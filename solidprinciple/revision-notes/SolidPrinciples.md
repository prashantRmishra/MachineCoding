Solid principles
Single responsibility Principle


class Employee{
	private String id;
	private String name;
	...

	//one reason to change 
	public updateName(String name){
		this.name = name;
	}

// another reason to change
	public void saveDetails(Employee e){
		String url = "jdbc://postgres://localhost:8080/dbName";
		String username = "";
		String password = "password";
		String query = "insert into employee values("",""...."")";
		Class.forName("org.mysql.jdbc.driver");
		Connection connection = DriverManger.getConnection(url,username, password);
		Statement statement = connection.createStatement();
		statement.executeQuery(query);
	}

// another reason to change
	public double calculateTax(Employee e){
		if(e.isPermanent()){
		//calculate tax for permanent employee
		}
		else {
		//calculate tax for the contract employee after 
		}
	}
}

public class EmployeeRepo{
	//details of db connection
	public boolean saveDetails(Employee e){
	// logic to save details in db
	}
}

public class TaxCalculator{
	//some constructor for dependency injections and all

	public double calculateTax(Employee e){
		//logic to calculate the tax.
	}
}


Open close principle:
Open for extension but closed for modification


Take example for Insurance company


//right now only giving HealthInsurance

public class InsuranceCalculator{
	

	public double getInsuranceAmount(HealthInsuranceCustomerProfile profile){
		if(profile.isLoyalCustomer()){
			//get 20% dicount 
			return amount-discount*amount/100;
		}
		else{
			 return amount;
		}
	}
}


//What if they start giving VehicleInsurance

above class needs to be modified 

public class InsuranceCalculator{
	

	public double getInsuranceAmount(HealthInsuranceCustomerProfile profile){
		if(profile.isLoyalCustomer()){
			//get 20% dicount 
			return amount-discount*amount/100;
		}
		else{
			 return amount;
		}
	}
	/// modification
	public double getInsuranceAmountForVehicl(VehicleInsuranceCustomerProfile profile){
	//similar logic
	}
}


What is the solution: don't do modification use try to use abstraction to do the job for you
	
interface CustomerProfile{
	public boolean isLoyalCustomer();
}

public class HealthInsuranceCustomerProfile implements CustomerProfile{
	@Override
	public boolean isLoyalCustomer(){
		//logic
	}
}
public class VehicleInsuranceCustomerProfile implements CustomerProfile{
	@Override
	public boolean isLoyalCustomer(){
		///logic
	}
}

class InsuranceCalculator{
	public double calculateInsuranceAmount(CustomerProfile profile){
		if(profile.isLoyalCustomer()){
			//get discounted price of insurance amount
		}
		else{
			get normal price of insurance amount
		}
	}
}

Liskov Substitution principle

An object should be replaciable with its subclasses without affecting the correctness of the code

public class Car{
	public int getInteriorWidth(){
		// get the interior width of the car
	}
}

public class RaceCar extends Car{
	public int getInteriorWidth(){
		//left un implemented as race cars don't have interior width
	}
	public int getCockpitWidth(){
		//get the cockpit width
	}
}
class App{
	psvm(s []){

		Car car1 = new Car();
		Car car2 = new RaceCar();
		sysout(car1.getInteriorWidth()); // will work fine
		if(car2.getInteriorWidth())/ this is wrong as the 
	}
}
strike at the root of inheritance

pulic class Vehicle{
	public int getWidth(){

	}
}

public class Car extends Vehicle{
	@Override
	public int getWidth(){
		return getInteriorWidth();
	}
	public int getInteriorWidth(){
		//logic 
	}
}

public class RaceCar extends Vehicle{
	@Override 
	public int getWidth(){
		return getCockpitWidth();
	}
	public int getCockpitWidth(){
		//logic
	}
}

tell don't ask

Take example of amazon where it gives discount  of 10% (2*NormalDisocunt) on purchase of inhouse products i.e AmazonBasics and NormalDiscount of 5% percent of all the other products

public class Product{
	double discount = //some discount percentange
	public double getDiscountAmount(){
		return discount;
	}
}
public class InhouseProduct extends Product{
	public double applyDiscont(){
		return discount*2;
	}
}

class App{
	psvm(s []){

		List<Product> list = new ArrayList<>();
		list.add(new Product());
		list.add(new InhouseProduct());
		for(Product p : list){
			if(p instanceOf InhouseProduct){
				sysout((InhouseProduct)p).applyDiscount());
			}
			else sysout(p.getDiscount());
		}
	}
}

///solution tell don't ask

public class InhouseProduct extends Product{
	@Override
	public double getDiscount(){
		return applyDiscount();
	}
	public double applyDiscont(){
		return discount*2;
	}
}


//in the main method
class App{
	psvm(s []){
		...
		for(Product p : list){
			sysout(p.getDiscount());
		}
		...
	}
}

Interface seggregation principle
a class should not be forced to use a method that it does not use
for example a swiss kife is capable of a lot of things like it can be use as a knife, cutter, opener and other things as well
But this is not a good seggregation, in sofware engineering a class first of all should have only related methods that it should do, like knife is noly ment for cutting it should not be used for picking a lock right ?



Example of Interface seggregation

Consider a office space where tools can be categorized as objects

public class WorkStation{
	public void print();
	pubic List<String> getPrintingSpool();
	public void scan();
	public void scanImage();
	public void fax();
	public void internetFax();
	....
}

public class PrinterScanner implements WorkStation{
	public void print(){
		//logic
	}
	public List<String> getPrintingSpool(){
		//logic
	}
	public void scan(){
		///logic
	}
	public void scanImage(){
		//logic
	}
	//-------forced methods----------
	public void fax(){
		//left unimplmented
	}
	public void internetFax(){
		//left unimplemented
	}
}
public class Scanner implements WorkStation{
	public void scan(){
		///logic
	}
	public void scanImage(){
		//logic
	}
	//-------forced methods----------

	public void print(){
		//left unimplmented
	}
	public List<String> getPrintingSpool(){
		//left unimplmented
	}

	public void fax(){
		//left unimplmented
	}
	public void internetFax(){
		//left unimplemented
	}
}

This is clear violation of Liskov substitution principle

Solution is seggragate the fat interface into different interfaces

public interface IPrint{
	public void print();
	public List<String> getPrintingSpool();
}
public interface IScan{
	public void scan();
	public void scanImage();
}
public interface IFax{
	public void fax();
	public void internetFax();
}

finally,
public class PrinterScanner implements IPrint,IScan{
	///
}
public class Scanner implemenets IScan{
	///
}

public class Fax implements IFax{
	///
}


Dependecy Inversion

High level modules should not depend on low level moduels, rather both should depend on abstraction

Abstractions should not depend on details, details should depend on abstraction

Consider example of e-commerce application like Flipkart

ProductCataog(High level) depends on ProductRepository(Low level)

public class ShoppingCart{

	//hard dependency on the ProductRepository
	ProductRepository repo = new ProductRepository();
	List<Product> list = repo.getProducts();
}

public class ProductRepository{

	public List<Products> getProducts(){
		List<Products> list = new ArrayList<>();
		//logic to get the all the products from the db
		return list;
	}
}

Issues:
Shopping cart is repsponsible or intializing the ProductRepository
and it directly dependent on the ProductRepository which is a clear violation of dependency inversion principle (as the high level is dependent on low level module)


public class Repository{
	public List<Product> getProducts();
}
public class ProductRepository implements Repository{
	@Override
	public List<Product> getProducts(){
		List<Product> list = new ArrayList<>();
		//logic
		return list;
	}
}
public class ProductFactory{
	public static ProductRepository getRepository(){
		return new ProductRepository();
	}
}
public class ProductCatalog{
	Repository repo = ProductFactory.getRepository();
	List<Product> list = repo.getProducts();
}

Now the High level ProductCatalog and the low level ProductRepository both depending on abstraction i.e Repository
This change is called as dip

