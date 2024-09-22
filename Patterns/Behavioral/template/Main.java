package Patterns.Behavioral.template;
public class Main{
	public static void main(String args[]){

		// create football game object 
		GameTemplate football = new Football();
		football.play();// play() will strictly follow the sequence of method execution defined in final play() method

		GameTemplate cricket = new Cricket();
		cricket.play();
	}
}