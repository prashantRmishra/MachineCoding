package Patterns.creational.builder.example2;

public class HouseMakerMain {
   public static void main(String args[]){
   //builder pattern without director class 
    Builder simpleHouseBuilder = new SimpleHouseBuilder();
    Builder luxuryHouseBuilder = new LuxuryHouseBuilder();
    House  simpleHouse = simpleHouseBuilder.addGarage().addWindows().buildFoundation().buildPool().buildWalls().getHouse();
    House  luxuryHouse = luxuryHouseBuilder.addGarage().addWindows().buildFoundation().buildPool().buildWalls().getHouse();
    System.out.println(simpleHouse);
    System.out.println(luxuryHouse);

    //with director class that oversees the creation of objects
    ConstructionDirector constructionDirectorOfSimpleHouse = new ConstructionDirector(new SimpleHouseBuilder());
    ConstructionDirector constructionDirectorOfLuxuryHouse = new ConstructionDirector(new LuxuryHouseBuilder());
    constructionDirectorOfSimpleHouse.constructHouse();
    System.out.println(constructionDirectorOfSimpleHouse.getHouse());
    constructionDirectorOfLuxuryHouse.constructHouse();
    System.out.println(constructionDirectorOfLuxuryHouse.getHouse());

   }
}
