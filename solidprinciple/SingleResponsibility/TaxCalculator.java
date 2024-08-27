package machinecodingexamples.solidprinciple.SingleResponsibility;

public class TaxCalculator {

    public void calculateTax(Employee employee) {
        if (employee.getEmployeeType().equals("fulltime")) {
            // tax calculation for full time employee
        } else if (employee.getEmployeeType().equals("contract")) {
            // tax calculation for contract type employee
        }
    }
}
