# Open Close Principle (OCP)

> Software component should be closed for modification, but open for extensions

*Closed for modification*:
New features added to the software component, should not have to modify the existing software component.

*Open for extension*:
A software component should be expandable to add new features or to add new behavior to it.


**Let's understand OCP with an example**

Consider that an Insurance company, **ABC**, provides Health Insurance to its customers.
The company gives discounts on the premiums to its loyal customers.

`InsurancePremiumDiscountCalculator` class

```java
/**
 * Responsible for calculating the insurance premium for the customer
 */
public class InsurancePremiumDiscountCalculator {
    public int calculatePremiumDiscountPercent(HealthInsuranceCustomerProfile customer) {
        if (customer.isLoyalCustomer()) {
            return 20;
        }
        return 0;
    }
}
```
`HealthInsuranceCustomerProfile` class
```java
public class HealthInsuranceCustomerProfile {

    public boolean isLoyalCustomer() {
        // logic to return if the given customer is loyal
        return new Random().nextBoolean();///This is just for returning true or false randomly
    }
}
```
Note: The above code works **fine for now**, but let's say in the future the company **ABC** also starts giving Vehicle insurance as well to its customers then, we will have to make significant changes in the code as below.

`VehicleInsuranceCustomerProfile` class needs to be created for customers taking vehicle insurance

```java
public class VehicleInsuranceCustomerProfile {
    public boolean isLoyalCustomer() {
        /// logic to return if the given customer is loyal
        return new Random().nextBoolean();///This is just for returning true or false randomly
    }
}
```
`InsurancePremiumDiscountCalculator` class needs to be **modified** by following the ways
```java
/**
 * Responsible for calculating the insurance premium for the customer
 */
public class InsurancePremiumDiscountCalculator {
    public int calculatePremiumDiscountPercent(HealthInsuranceCustomerProfile customer) {
        if (customer.isLoyalCustomer()) {
            return 20;
        }
        return 0;
    }
    public int calculatePremiumDiscountPercent(VehicleInsuranceCustomerProfile customer) {
        if (customer.isLoyalCustomer()) {
            return 20;
        }
        return 0;
    }
}
```
Note: The above changes are **not following OCP** because we are modifying the existing code to accommodate the new feature (Vehicle insurance)

**Revamping the code as per OCP**

`InsurancePremiumDiscountCalculator` class should be designed like this to accommodate new features

```java
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
```
`CustomerProfile` interface for all types of customers(Health Insurance, Vehicle insurance,etc)
```java
public interface CustomerProfile {
    public boolean isLoyalCustomer(); 
}
```
`HealthInsuranceCustomerProfile` class
```java
public class HealthInsuranceCustomerProfile implements CustomerProfile {
    @Override
    public boolean isLoyalCustomer() {
        return new Random().nextBoolean();
    }
}
```

`VehicleInsuranceCustomerProfile` class 

```java
public class VehicleInsuranceCustomerProfile implements CustomerProfile {
    @Override
    public boolean isLoyalCustomer() {
        return new Random().nextBoolean();
    }
}
```
Now, the above software component **follows OCP**(Open Close Principle)

Let's try adding another feature of **Home Insurance**

For this new feature addition, we won't have to modify the existing code, we will just have to extend it by adding a new class called `HomeInsuranceCustomerProfile`

`HomeInsuranceCustomerProfile` class

```java
public class HomeInsuranceCustomerProfile implements CustomerProfile {
    @Override
    public boolean isLoyalCustomer() {
        return new Random().nextBoolean();
    }
}
```
And that is it, it works like a charm.


**Key takeaways:**

- Ease of adding new features.
- Leads to the minimal cost of development and testing.
- Open close principle often requires decoupling, which, in turn, automatically follows **Single Responsibility Principle** (SRP).

_Important_:
1. *Solid principles are all intertwined and interdependent.*
2. *Solid principles are more effective when they are used together.*