package Patterns.Behavioral.chainOfResponsibility;

public class InfoLogger extends AbstractLogger {
    private String className = this.getClass().getSimpleName();
    private String logger   = "INFO";
    public InfoLogger(){
        this.LEVEL = 2;
    }

    @Override
    void write(String message) {
        System.out.println(className+":"+logger+":"+message);
    }
    
}
