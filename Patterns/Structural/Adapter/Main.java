package Patterns.Structural.Adapter;

class Main{
    public static void main(String args[]){
        UsbPort usbPort1 = new UsbPort();
        UsbCable usbCable = new UsbCable();
        usbPort1.plug(usbCable);
        //plugging microUsbCable to UsbPort via adapter
        MicroUsbToUsbAdapter microUsbToUsbAdapter = new MicroUsbToUsbAdapter(new MicroUsbCable());

        UsbPort usbPort2 = new UsbPort();
        usbPort2.plug(microUsbToUsbAdapter);
    }
}