Template is one of the behavioral design patterns, an abstract class defines a set of ways/template to execute its methods.

Its subclasses can override/implement the these methods but the invocation is to be in the same way as defined by an abstract class

Lets understand this with an example: 

**Key concepts**
*Template*: An abstract class defining the structure/way/template of the algorithm
*Concrete Implementation*: ConcreteImplementation of Template
*Client*: Client that will make use of this Template

```java
public abstract class GameTemplate{
	//these below methods can be overridden based on type of game
	public abstract void initialize();
	public abstract void startPlay();
	public abstract void  endPlay();

    // all the subclasses must use this same method to play the game i.e. following the same template present in this method,
	// hence it is declared as final.
	public final void play(){
		initialize();
		startPlay();
		endPlay();
	}
}

public class Cricket extends GameTemplate{


	@Override
	public void initialize(){
		System.out.println("Cricket has been initialized");
	}
	@Override
	public void startPlay(){
		System.out.println("Cricket game has been started");
	}
	@Override
	public void endPlay(){
		System.out.println("Cricket game has ended");
	}

}

public class Football extends GameTemplate{

	@Override
	public void initialize(){
		System.out.println("Football has been initialized");
	}
	@Override
	public void startPlay(){
		System.out.println("Football game has been started");
	}
	@Override
	public void endPlay(){
		System.out.println("Football game has ended");
	}

}

public class Main{
	public static void main(String args[]){

		// create football game object 
		GameTemplate football = new Football();
		football.play();// play() will strictly follow the sequence of method execution defined in final play() method

		GameTemplate cricket = new Cricket();
		cricket.play();
	}
}

```

Output:

```output:
Football has been initialized
Football game has been started
Football game has ended
Cricket has been initialized
Cricket game has been started
Cricket game has ended
```

note: The code follows all the design principles like LSP, ISP, SRP, OCP