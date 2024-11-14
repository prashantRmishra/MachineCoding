
public class ExternalButton {
   ExternalDispatcher dispatcher = new ExternalDispatcher();
    
    public void pressButton(int destinationFloor, Direction direction) {
        dispatcher.submitExternalRequest(destinationFloor,direction);
    }

}
