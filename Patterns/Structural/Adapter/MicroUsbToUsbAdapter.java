class MicroUsbToUsbAdapter extends UsbCable{
    public MicoUsbToUsbAdapter(MicroUsbCable microUsbCable){
        microUsbCable.plugMicroUsbCable();
    }
    // it can also override the plugUsbCable() if needed
}