package Patterns.Behavioral.chainOfResponsibility;

public class ErrorLogger extends AbstractLogger {
    private String className = this.getClass().getSimpleName();
    private String logger   = "ERROR";
    public ErrorLogger(){
        this.LEVEL = 3;
    }

    @Override
    void write(String message) {
        System.out.println(className+":"+logger+":"+message);
    }
    
}
