import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class DistributedLoggingSystem{
    public static void main(String[] args) {
        CentralLogPersister logPersister  = new CentralLogPersister();
        Logger fatalLogger = new FatalLogger(0,logPersister);
        Logger errorLogger = new ErrorLogger(1,logPersister);
        Logger debugLogger = new DebugLogger(2,logPersister);
        Logger warnLogger = new WarningLogger(3,logPersister);
        Logger infoLogger = new InfoLogger(4,logPersister);
        fatalLogger.setNextLogger(errorLogger);
        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(warnLogger);
        warnLogger.setNextLogger(infoLogger);

        LogHandler handler = new LogHandler(fatalLogger);
        handler.logGenerator();

        new Thread(()->{
            while(true){
                System.out.println(logPersister.strategy.search("FATAL"));// default is search by level strategy
                logPersister.setSearchStrategy(new SearchByKeyWordStrategy(logPersister.logBook));
                System.out.println(logPersister.strategy.search("is is de"));
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    System.out.println("failed to get search result, thread interrupted");
                }
            }
        }).start();


    }

}
class Log{
    String msg;
    int level;
    LocalDateTime timestamp;
    public Log(String msg, int level){
        this.msg = msg;
        this.level = level;
        timestamp = LocalDateTime.now();
    }
    @Override
    public String toString() {
        return "Log [msg=" + msg + ", level=" + level + ", timestamp=" + timestamp + "]";
    }
}
class LogHandler{
    Logger logger;
    public LogHandler(Logger logger){
        this.logger = logger;
    }

    public void newLogMessage(Log log){
        logger.log(log, log.level);
    }

    public void logGenerator(){
        String levels[] = {"this is fatal log", "this is error log","this is debug", "this is warning log","this is info log"};
        Random random = new Random();
        new Thread(()->{
            while(true){
                int index = random.nextInt(levels.length);
                Log log  = new Log(levels[index], index);
                newLogMessage(log);
                try {
                    Thread.sleep(1000);
                } 
                catch (InterruptedException e) {
                    System.out.println("Thread interrupted  during log creation");
                }
            }
        }).start();
        
    }
}

abstract class Logger {
    CentralLogPersister logPersister;
    int logLevel;
    Logger nextLogger;
    public Logger(int logLevel, CentralLogPersister logPersister){
        this.logLevel = logLevel;
        this.logPersister  = logPersister;
    }
    public void setNextLogger(Logger logger){
        this.nextLogger = logger;
    }
    public void log(Log log , int level){
        if(level<=this.logLevel){
            print(log);
        }
        else{
            if(nextLogger==null){
                //severity is less that the lowest level of logger hence directly logging it
                print(log);
                return;
            }
            nextLogger.log(log, level);
        }
    }
    abstract void print(Log log);
}
//info, warn, debug, error, fatal
class FatalLogger extends Logger{
    public FatalLogger(int logLevel,CentralLogPersister persister){
        super(logLevel, persister);
    }
    @Override
    public void print(Log log){
        logPersister.save(log, "FATAL");
        System.out.println("FATAL: "+ log.msg);
    }
}
class ErrorLogger extends Logger{
    public ErrorLogger(int level,CentralLogPersister persister){
        super(level,persister);
    }
    @Override
    public void print(Log log){
         logPersister.save(log, "ERROR");
        System.out.println("ERROR: "+ log.msg);
    }
}
class DebugLogger extends Logger{
    public DebugLogger(int level,CentralLogPersister persister){
        super(level,persister);
    }
    @Override
    public void print(Log log){
         logPersister.save(log, "DEBUG");
        System.out.println("DEBUG: "+ log.msg);
    }
}
class WarningLogger extends Logger{
    WarningLogger(int level,CentralLogPersister persister){
        super(level,persister);
    }
    @Override
    public void print(Log log){
         logPersister.save(log, "WARN");
        System.out.println("WARN: "+ log.msg);
    }
}
class InfoLogger extends Logger{
    public InfoLogger(int level,CentralLogPersister persister){
        super(level,persister);
    }
    @Override
    public void print(Log log){
         logPersister.save(log, "INFO");
        System.out.println("INFO: "+ log.msg);
    }
}

class CentralLogPersister{
    Map<String,List<Log>> logBook;
    SearchStrategy strategy;
    public CentralLogPersister(){
        logBook =  new ConcurrentHashMap<>();
        strategy = new SearchByLevelStrategy(logBook);
    }

    public void save(Log log, String level){
        logBook.putIfAbsent(level, new CopyOnWriteArrayList<>());
        logBook.get(level).add(log);
    }
    public void setSearchStrategy(SearchStrategy strategy){
        this.strategy = strategy;
    }
}
interface SearchStrategy{
    public List<Log> search(String token);
}
class SearchByLevelStrategy implements SearchStrategy {
    Map<String,List<Log>> logBook;
    public SearchByLevelStrategy(Map<String,List<Log>> logBook){
        this.logBook = logBook;

    }
    @Override
    public List<Log> search(String token) {
        return logBook.getOrDefault(token, new ArrayList<>());
    }
}
class SearchByKeyWordStrategy implements SearchStrategy {
    Map<String,List<Log>> logBook;
    public SearchByKeyWordStrategy(Map<String,List<Log>> logBook){
        this.logBook = logBook;
    }
    @Override
    public List<Log> search(String token) {
        return logBook.values().stream().flatMap(l-> l.stream()).filter(log->  log.msg.toLowerCase().contains(token.toLowerCase())).toList();
    }
}


/*
 * output:
 * 
[]
[]
FATAL: this is fatal log
WARN: this is warning log
DEBUG: this is debug
INFO: this is info log
[Log [msg=this is fatal log, level=0, timestamp=2025-07-26T18:04:47.820278]]
[Log [msg=this is debug, level=2, timestamp=2025-07-26T18:04:49.833083]]
FATAL: this is fatal log
WARN: this is warning log
INFO: this is info log
ERROR: this is error log
[Log [msg=this is fatal log, level=0, timestamp=2025-07-26T18:04:47.820278], Log [msg=this is fatal log, level=0, timestamp=2025-07-26T18:04:51.842164]]
[Log [msg=this is debug, level=2, timestamp=2025-07-26T18:04:49.833083]]
ERROR: this is error log
WARN: this is warning log
DEBUG: this is debug
FATAL: this is fatal log
[Log [msg=this is fatal log, level=0, timestamp=2025-07-26T18:04:47.820278], Log [msg=this is fatal log, level=0, timestamp=2025-07-26T18:04:51.842164], Log [msg=this is fatal log, level=0, timestamp=2025-07-26T18:04:58.869429]]
[Log [msg=this is debug, level=2, timestamp=2025-07-26T18:04:49.833083], Log [msg=this is debug, level=2, timestamp=2025-07-26T18:04:57.864041]]
FATAL: this is fatal log
FATAL: this is fatal log
 * 
*/