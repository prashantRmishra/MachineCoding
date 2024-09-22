package Patterns.Behavioral.template;

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