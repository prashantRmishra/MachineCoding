package machinecodingexamples.solidprinciple.InterfaceSegregation;

import machinecodingexamples.solidprinciple.InterfaceSegregation.AfterInterfaceSegregation.IFax;
import machinecodingexamples.solidprinciple.InterfaceSegregation.AfterInterfaceSegregation.IPrint;
import machinecodingexamples.solidprinciple.InterfaceSegregation.AfterInterfaceSegregation.IScan;

/**
 * 
 * You must have seem xerox work station device which has all the features in one like printing, scanning, xerox,
 * fax etc
*/
public class XeroxWorkCenter implements IPrint,IScan,IFax {

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
        // real scan photo code ̰
    }

    @Override
    public void fax() {
        // real fax code
    }

    @Override
    public void internetFax() {
        // real internet fax code
    }

}
