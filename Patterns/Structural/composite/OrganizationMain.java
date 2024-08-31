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
        ceo.addSubordinate(ed1);
        ceo.addSubordinate(ed2);
        
        //vp's reporting to their respective ed's
        ed1.addSubordinate(vp1);
        ed1.addSubordinate(vp2);
        ed2.addSubordinate(vp3);

        //sde's reporting to vp's
        vp1.addSubordinate(sde1);
        vp1.addSubordinate(sde2);

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
