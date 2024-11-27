package Patterns.Behavioral;

interface Logger{
    public void log(String msg, int level);
}
class FatalLogger implements Logger{
    private final int level = 1;
    private Logger nextLogger;
    public FatalLogger(Logger logger){
        this.nextLogger = logger;
    }
    @Override
    public void log(String msg,int level){
        if(this.level >=level){
            System.out.println(this.getClass().toString()+" "+ msg);
        }
        else{
            nextLogger.log(msg, level);
        }
    }
}
class ErrorLogger implements Logger{
    private final int level = 2;
    private Logger nextLogger;
    public ErrorLogger(Logger logger){
        this.nextLogger = logger;
    }
    @Override
    public void log(String msg,int level){
        if(this.level >=level){
            System.out.println(this.getClass().toString()+" "+ msg);
        }
        else{
            nextLogger.log(msg, level);
        }
    }
}
class InfoLogger implements Logger{
    private final int level = 3;
    private Logger nextLogger;
    public InfoLogger(Logger logger){
        this.nextLogger = logger;
    }
    @Override
    public void log(String msg,int level){
        if(this.level >=level){
            System.out.println(this.getClass().toString()+" "+ msg);
        }
        else{
            nextLogger.log(msg, level);
        }
    }
}
class DebugLogger implements Logger{
    private final int level = 4;
    private Logger nextLogger;
    public DebugLogger(Logger logger){
        this.nextLogger = logger;
    }
    @Override
    public void log(String msg,int level){
        if(this.level >=level){
            System.out.println(this.getClass().toString()+" "+ msg);
        }
        else{
            nextLogger.log(msg, level);
        }
    }
}
class GeneralLogger implements Logger{
    public GeneralLogger(){
    }
    @Override
    public void log(String msg,int level){
       System.out.println(this.getClass().toString()+" "+ msg);
    }
}

public class Main {
    public static void main(String[] args) {
        //FATAL> ERROR > INFO > DEBUG
        Logger generalLogger = new GeneralLogger();// to  handle all sorts of logging messages of all the level not supported by other loggers
        Logger debugLogger = new DebugLogger(generalLogger);
        Logger infoLogger = new InfoLogger(debugLogger);
        Logger errorLogger = new ErrorLogger(infoLogger);
        Logger logger = new FatalLogger(errorLogger);

        logger.log("This is a fatal message", 1);
        logger.log("This is a error message", 2);
        logger.log("This is a info message", 3);
        logger.log("This is a debug message", 4);
        logger.log("This is some other log message", 5);
    }
}
