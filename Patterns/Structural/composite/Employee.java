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

    public void removeSubordinate(Employee e){
        this.subOrdinates.remove(e);
    }
  
    public List<Employee> getSubOrdinates() {
        return subOrdinates;
    }
    public void addSubordinate(Employee e) {
        this.subOrdinates.add(e);
    }
    @Override
    public String toString() {
        return "Employee [name=" + name + ", department=" + department + ", id=" + id+"]";
    }
}
