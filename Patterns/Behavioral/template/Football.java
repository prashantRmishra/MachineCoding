package Patterns.Behavioral.template;

public class Football extends GameTemplate{

	@Override
	public void initialize(){
		System.out.println("Football has been intialized");
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