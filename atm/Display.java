package atm;
import java.util.List;

public class Display {
    private String textShow = null;
    private Button cancel;
    private Button exit;
    private List<Button> nums;
    public Display(){
        textShow = ATMMessage.WELCOME.getMessage();
    }

    public void updateText(ATMMessage s){
        textShow = s.getMessage();
        System.out.println(textShow);//log the message
    }
    public String getText(){
        return this.textShow;
    }
}
