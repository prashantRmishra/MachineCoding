package Patterns.Behavioral.chainOfResponsibility;

public class Main {
    public static AbstractLogger intializeLoggers(){
        AbstractLogger errorLogger = new ErrorLogger(); //LEVEL = 3;
        AbstractLogger infoLogger = new InfoLogger(); //LEVEL = 2;
        AbstractLogger debugLogger = new DebugLogger(); // LEVEL = 1;
        errorLogger.setNextLogger(infoLogger);
        infoLogger.setNextLogger(debugLogger);
        return errorLogger;// return the highest priority Logger first


    }
    public static void main(String args[]){
        // initialize the chain of responsible objects
        AbstractLogger logger  = intializeLoggers();
    
        //pass the request down the responsibility chain
        //logging level 3 logger
        logger.logMessage(3, "log this error message");
        //loggin level 2 logger
        logger.logMessage(2, "info level log message");
        //logging level 1 logger
        logger.logMessage(1, "debug level log message");
    }
}
