package Patterns.Structural.Adapter;

class MicroUsbCable{
    public boolean isPluggedIn = false;
    public void plugMicroUsbCable(){
        this.isPluggedIn = true;
    }
}