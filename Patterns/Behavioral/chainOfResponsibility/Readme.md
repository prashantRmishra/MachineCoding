As the name suggests, a chain of responsibility creates a chain of receiver objects to handle requests.
This pattern decouples the request's sender and receiver based on the request type.
This pattern comes under the Behavioural pattern.

In this pattern each receiver object of the request has a reference to the next object if it can not handle the request, the request is passed down to the next receiver in the chain.


Let's understand this by taking the example of a logging mechanism that logs messages based on the level of the message (request)

`AbstractLogger`
```java
package Patterns.Behavioral.chainOfResponsibility;

public abstract class AbstractLogger{
    /**
     * TRACE < DEBUG < INFO < WARN < ERROR < FATAL
     * which means if the level is INFO, then INFO, WARN, ERROR and FATAL messages will be logged
     * but if the level is ERROR then only ERROR and FATAL messages will be logged 
    */
    //higher the number higher the priority
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
        //If the logging level of the message is greater than the current Logger's LEVEL then it will be logged 
        //example if level = ERROR and this.LEVEL = INFO then the message will be logged as INFO has a lower priority than ERROR
        if(this.LEVEL <=level){
            write(message);
        }
        // else the message/request will be passed down to the next logger/object in the chain
        else{
            if(nextLogger!=null){
                nextLogger.logMessage(level, message);
            }
        }
    }
    abstract void write(String message);
}
```
`ConcreteLoggers`

```java
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
```

`Main`

```java
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
```


Output:

```output
ErrorLogger:ERROR:log this error message
InfoLogger:INFO:info level log message
DebugLogger:DEBUG:debug level log message
```


**Key points**

- Follows LSP (The Liskov substitution principle i.e. solid design pattern).
- Follows SRP of solid principle.
- Follows OCP of solid principle as we can add more Loggers like trace, fatal, etc without modifying existing code at all.
- Follows ISP as well.

