package solidprinciple.InterfaceSegregation;

import  solidprinciple.InterfaceSegregation.AfterInterfaceSegregation.IPrint;

public class CanonPrinter implements IPrint {

    @Override
    public void print() {
        // real printing code
    }

    @Override
    public void getPrintSpoolDetails() {
        // real get print spool details code
    } 
}
