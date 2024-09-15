package Patterns.Behavioral.chainOfResponsibility;

public class DebugLogger extends AbstractLogger {
    private String className = this.getClass().getSimpleName();
    private String logger   = "DEBUG";
    public DebugLogger(){
        this.LEVEL = 1;
    }

    @Override
    void write(String message) {
        System.out.println(className+":"+logger+":"+message);
    }
    
}
