package Patterns.Structural.Adapter;

class UsbPort{
    public boolean isPortAvailable  = true;

    public void plug(UsbCable usbCable){
        this.isPortAvailable = false;
        usbCable.plugUsbCable();
    }
}