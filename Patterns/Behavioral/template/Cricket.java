package Patterns.Behavioral.template;

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