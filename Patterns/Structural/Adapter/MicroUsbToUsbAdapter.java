package Patterns.Structural.Adapter;

class MicroUsbToUsbAdapter extends UsbCable{
    public MicroUsbToUsbAdapter(MicroUsbCable microUsbCable){
        microUsbCable.plugMicroUsbCable();
    }
    // it can also override the plugUsbCable() if needed
}