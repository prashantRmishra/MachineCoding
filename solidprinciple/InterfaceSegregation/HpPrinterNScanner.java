package machinecodingexamples.solidprinciple.InterfaceSegregation;

import machinecodingexamples.solidprinciple.InterfaceSegregation.AfterInterfaceSegregation.IPrint;
import machinecodingexamples.solidprinciple.InterfaceSegregation.AfterInterfaceSegregation.IScan;

public class HpPrinterNScanner implements IPrint,IScan {

    @Override
    public void print() {
        // real printing code
    }

    @Override
    public void getPrintSpoolDetails() {
        // real get print spool details code
    }

    @Override
    public void scan() {
        // read scanning code
    }

    @Override
    public void scanPhoto() {
        // real scan photo code 
    }
}