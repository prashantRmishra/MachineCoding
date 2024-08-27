package machinecodingexamples.solidprinciple.InterfaceSegregation;

/**
 * @ImultiFunction interface has methods methods related to all output devices present in office space
 * for devices like Printer, Scanner, Fax machines etc
*/
public interface IMultiFunction {
    public void print();
    public void getPrintSpoolDetails();
    public void scan();
    public void scanPhoto();
    public void fax();
    public void internetFax();
}
