# Interface Segregation Principle

## No client should be forced to depend a method it does not use

Before Interface segregation Principle:

*Consider example of office space where various output devices are represented using objects*

`IMultiFunction` Interface
```java
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
```
Now implementing the above common interface for various devices

`XeroxWorkCenter` class having all capabilities 
```java
/**
 * 
 * You must have seen xerox work station device which has all the features in one like printing, scanning, xerox,
 * fax etc
*/
public class XeroxWorkCenter implements IMultiFunction {

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
```
`HpPrinterNScanner` class having printing and scanning capabilities

```java
public class HpPrinterNScanner implements IMultiFunction {

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

    //Since HpPrinterNScanner has only printing and scanning abilities fax() and internetFax() will have empty body
    @Override
    public void fax() {}

    @Override
    public void internetFax() {}
    
}
```
`CanonPrinter` class having only printing capability
```java
public class CanonPrinter implements IMultiFunction {

    @Override
    public void print() {
        // real printing code
    }

    @Override
    public void getPrintSpoolDetails() {
        // real get print spool details code
    }
    
    //Since the CanonPrinter has only printing ability rest of all the method will have empty body
    @Override
    public void scan() {}

    @Override
    public void scanPhoto() {}

    @Override
    public void fax() {}

    @Override
    public void internetFax() {}
    
}
```


**Techniques to identify the violation of ISP**

- **Fat interfaces** (Interfaces having two many method declarations)
- **Interfaces with low cohesion** (interfaces having methods that are not likely to be related to each other)
- **Empty methods implementation **( when classes are forced to implement methods that they don't use, they leave the implementation of the methods with blank body)

**After Interface segregation principle**:

```java
public interface IPrint {
    public void print();
    public void getPrintSpoolDetails();
}
```
```java
public interface IScan {
    public void scan();
    public void scanPhoto();
}
```
```java
public interface IFax {
    public void fax();
    public void internetFax();
}
```
```java
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
```
```java
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
```
```java
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
```
Each Interface has single responsibility and is much cleaner now.



**Relation that ISP holds with other SOLID principles**

*Single responsibility*
After the segregation of the interfaces into different interfaces, now all the interfaces like IPrint, IScan have single responsibility

*Liskov substitution*
Due to the segregation now all the classes (implementing the interfaces) follow Liskov substitution, as all the subtypes or implementing classes can be replaced with their interface reference variable 