import java.util.Random;
public class BrowserHistorySimulator{
    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("google.com", new int[]{1,3,2,5,1,1,2,3,5,2,2}, new String[]{"oracle.com", "amazon.in", "flipkart.com", "stackoverflow.com", "openai.chatgpt.com", "bard.com", "gemini.com/googleio/machine/code", "ternaengg.com/student/score", "ternaengg/com/studnet/oral", "google.com/googletv/robot.txt"});
        browserHistory.simulator();
    }
}
class BrowserHistory{
    Node head;
    Node tail;
    Node current;
    String urls[];
    int steps[];
    Random random;
    String methods[] = {"back", "forward", "visit","visit","visit","visit", "back","forward","forward","visit","visit","back","forward","visit","visit","forward","forward","forward"};
     public BrowserHistory(String homePage, int[] steps, String urls[]){
        //navigate to the home page of the browser
        visit(homePage);// this will become the landing page for the browser
        this.steps = steps;
        random = new Random();
        this.urls = urls;
    }

    public void simulator(){
        new Thread(()->{
            while(true){
                int step = steps[random.nextInt(steps.length)];
                String method = methods[random.nextInt(methods.length)];
                if(method.equals("visit")){
                    System.out.println("visit=> "+visit(urls[random.nextInt(urls.length)]));
                }
                else if(method.equals("back")){
                   System.out.println("back ("+step+")=> "+back(step));
                }
                else{
                    System.out.println("forward ("+step+")=> "+forward(step));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName()+" interrupted !!");
                }
            }
        }).start();
    }
    public String back(int steps){
        if(current ==null) return null;
       
        while(current.left!=null && steps-->0){
            current = current.left;
        }
        return current.url;

    }
    public String forward(int steps){
        if(current ==null) return null;
        while(current.right!=null && steps-->0){
            current = current.right;
        }
        return current.url;
    }

    public String visit(String url){
        if(current!=null)
            System.out.println("current was at "+ current.url);
        Node node = new Node(url);
        if(head==null && tail ==null && current ==null){
            head = node;
            tail  =node;
            current =node;
        }
        else{
            current.right = node;
            node.left= current;
            tail = node;
            current = node;
        }
        return current.url;
    }
}
class Node{
    Node left;
    Node right;
    String url;
    public Node(String url){
        this.url = url;
    }
}


/*
 * 
 * 
back (2)=> google.com
current was at google.com
visit=> gemini.com/googleio/machine/code
forward (1)=> gemini.com/googleio/machine/code
forward (5)=> gemini.com/googleio/machine/code
current was at gemini.com/googleio/machine/code
visit=> gemini.com/googleio/machine/code
current was at gemini.com/googleio/machine/code
visit=> bard.com
back (2)=> gemini.com/googleio/machine/code
forward (2)=> bard.com
forward (5)=> bard.com
current was at bard.com
visit=> openai.chatgpt.com
current was at openai.chatgpt.com
visit=> google.com/googletv/robot.txt
back (5)=> google.com
forward (3)=> bard.com
back (1)=> gemini.com/googleio/machine/code
forward (2)=> openai.chatgpt.com
back (2)=> gemini.com/googleio/machine/code
back (2)=> google.com
forward (1)=> gemini.com/googleio/machine/code
current was at gemini.com/googleio/machine/code
visit=> oracle.com
forward (2)=> oracle.com
forward (1)=> oracle.com
current was at oracle.com
visit=> bard.com
current was at bard.com
visit=> bard.com
current was at bard.com
visit=> flipkart.com
current was at flipkart.com
visit=> ternaengg/com/studnet/oral
current was at ternaengg/com/studnet/oral
visit=> google.com/googletv/robot.txt
current was at google.com/googletv/robot.txt
visit=> gemini.com/googleio/machine/code
forward (2)=> gemini.com/googleio/machine/code
current was at gemini.com/googleio/machine/code
visit=> openai.chatgpt.com
forward (2)=> openai.chatgpt.com
current was at openai.chatgpt.com
visit=> openai.chatgpt.com
forward (2)=> openai.chatgpt.com
current was at openai.chatgpt.com
visit=> openai.chatgpt.com
current was at openai.chatgpt.com
visit=> bard.com
forward (3)=> bard.com

*/