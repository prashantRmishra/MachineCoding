package Patterns.Behavioral.chainOfResponsibility;

public abstract class AbstractLogger{
    /**
     * TRACE < DEBUG < INFO < WARN < ERROR < FATAL
     * which means if the level is INFO, then INFO, WARN,ERROR and FATAL messages will be logged
     * but if the level is ERROR then only ERROR and FATAL messages will be logged 
    */
    
    public static int DEBUG = 1;
    public static int INFO = 2;
    public static int ERROR = 3;
    protected int LEVEL;
    //next Logger in the chain of responsibility
    private AbstractLogger nextLogger;
    public void setNextLogger(AbstractLogger logger){
        this.nextLogger = logger;
    }
    public void logMessage(int level, String message){
        // if the logging level of the message is greater than the current Logger's LEVEL then it will be logged 
        //example if level = ERROR and this.LEVEL = INFO then the message will be logged as INFO has high priority than ERROR
        if(this.LEVEL <=level){
            write(message);
        }
        // else the message/request will be passed down to next logger/object in the chain
        else{
            if(nextLogger!=null){
                nextLogger.logMessage(level, message);
            }
        }
    }
    abstract void write(String message);
}