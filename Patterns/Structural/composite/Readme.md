Composite design pattern is a type of structural design pattern and is used when you want to treat **group of similar objects as one**.

This pattern creates **a class containing group of its own objects**. This class provides ways to modify its group of same objects

Example: Employee Hierarchy of an organisation

![composite design pattern](https://dev-to-uploads.s3.amazonaws.com/uploads/articles/aflr4a3qp8giodv274az.png)


`Employee.java`

```java
package Patterns.Structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private String department;
    private String id;
    private List<Employee> subOrdinates;

    public Employee(String name, String department, String id) {
        this.name = name;
        this.department = department;
        this.id = id;
        this.subOrdinates = new ArrayList<>();
    }
  
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Employee> getSubOrdinates() {
        return subOrdinates;
    }
    public void addSubordinates(Employee e) {
        this.subOrdinates.add(e);
    }
    @Override
    public String toString() {
        return "Employee [name=" + name + ", department=" + department + ", id=" + id+"]";
    }
}
```
`Main.java`

```java
package Patterns.Structural.composite;

public class OrganizationMain {
    public static void main(String args[]){
        //creating different employees like ceo,ed(executive director),vp and sde's
        Employee ceo = new Employee("Dishant Patil", "CEO", "1");
        Employee ed1 = new Employee("Sushma Mishra", "Head Markets", "2");
        Employee vp1 = new Employee("Alok Mishra", "Markets", "3");
        Employee vp2 = new Employee("Dishant Patil", "Markets", "4");
        Employee ed2 = new Employee("Asutosh Dwivedi", "Head Credits", "5");
        Employee vp3 = new Employee("Arunodaya Pandey", "Credits", "6");
        Employee sde1 = new Employee("Prashant Mishra", "Markets", "7");
        Employee sde2 = new Employee("Sandeep Padhi", "Credits", "8");

        //updating subordinates based on department type
        //ed's reporting to ceo
        ceo.addSubordinates(ed1);
        ceo.addSubordinates(ed2);
        
        //vp's reporting to their respective ed's
        ed1.addSubordinates(vp1);
        ed1.addSubordinates(vp2);
        ed2.addSubordinates(vp3);

        //sde's reporting to vp's
        vp1.addSubordinates(sde1);
        vp1.addSubordinates(sde2);

        //printing the hierarchy
        System.out.println(ceo);
        for(Employee e  : ceo.getSubOrdinates()){
            System.out.println(e);
            for(Employee subordinate : e.getSubOrdinates()){
                System.out.println(subordinate);
            }
        }
    }
}
```

**Output**

```output
Employee [name=Dishant Patil, department=CEO, id=1]
Employee [name=Sushma Mishra, department=Head Markets, id=2]
Employee [name=Alok Mishra, department=Markets, id=3]
Employee [name=Dishant Patil, department=Markets, id=4]
Employee [name=Asutosh Dwivedi, department=Head Credits, id=5]
Employee [name=Arunodaya Pandey, department=Credits, id=6]
```

