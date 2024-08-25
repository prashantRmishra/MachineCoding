package machinecodingexamples.solidprinciple.OpenClosePrinciple;

/**
 * Responsible for calculating the insurance premium for the customer
 */
public class InsurancePremiumDiscountCalculator {
    public int calculatePremiumDiscountPercent(CustomerProfile customer) {
        if (customer.isLoyalCustomer()) {
            return 20;
        }
        return 0;
    }
}
