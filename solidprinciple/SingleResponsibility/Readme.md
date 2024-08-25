# Single Responsibility Principle

> Every software component should have one and one responsibility only

Software component can be `class`, `method` or `module`

Example a swiss-army knife is multipurpose tool which violates the single responsibility principle of software development, instead a knife is good example that follows Single responsibility (as it can only be used for cutting unlike the swiss-army knife that can be used for cutting, opening can, key scissors,etc)

Since the change is constant be it real world or in software development, the definition of the Single Responsibility Principle also changes accordingly 

> Every software component should have one and only one reason to change

---

**There are three reasons for which change can occur in the below class `Employee`**

1. Change in Employee attribute
2. Change in Database
3. Change in Tax calculation

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Employee class has details of employee
 * This class is responsible for saving employee details, getting tax of
 * employee and getting
 * details of employee like name, id, address, contact,etc
 */
public class Employee {
    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String contactNumber;
    private String employeeType;

    public void save() {
        String insert = MyUtil.serializeIntoString(this);
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc://mysql://localhost:8080/MyDb", "root", "password");
            statement = connection.createStatement();
            statement.execute("insert into employee values (" + insert + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void calculateTax() {
        if (this.getEmployeeType().equals("fulltime")) {
            // tax calculation for full time employee
        } else if (this.getEmployeeType().equals("contract")) {
            // tax calculation for contract type employee
        }
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

}
```
Since the SRP (Single Responsibility Principle) recommends **only one reason for change** in the class, we will have to make some modifications in the Employee class

---
Changes in accordance with SIP
--

Now , there is only one reason for which change can occur in `Employee` class

*Reason for change*: Change in `Employee` attribute

```java
/**
 * Employee class has details of employee
 */
public class Employee {
    private String employeeId;
    private String employeeName;
    private String employeeAddress;
    private String contactNumber;
    private String employeeType;

    public void save() {
       new EmployeeRepository().save(this);
    }

    public void calculateTax() {
       new TaxCalculator().calculateTax(this);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

}
```

---

Also, there is only one reason for which change can occur in `EmployeeRepository` class

*Reason for change*: Change in database

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class EmployeeRepository {

    public void save(Employee employee) {
         String insert = MyUtil.serializeIntoString(employee);
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc://mysql://localhost:8080/MyDb", "root", "password");
            statement = connection.createStatement();
            statement.execute("insert into employee values (" + insert + ")");

        } catch (Exception e) {
            e.printStackTrace();
        }
}

}
```
---

Finally, there is only one reason for which change can occur in `TaxCalculator` class

*Reason for change*: Change in Tax calculation

```java
public class TaxCalculator {

    public void calculateTax(Employee employee) {
        if (employee.getEmployeeType().equals("fulltime")) {
            // tax calculation for full time employee
        } else if (employee.getEmployeeType().equals("contract")) {
            // tax calculation for contract type employee
        }
    }
}
```
---

*Note: All the 3 classes now follow the Single Responsibility Principle, thus following both the definitions*

When creating classes or any software component keep in mind: **Aim for high cohesion and loose coupling** 

**Every software component should have one and one responsibility only** and 
**Every software component should have one and only one reason to change**