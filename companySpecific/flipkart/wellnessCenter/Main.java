package companySpecific.flipkart.wellnessCenter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import companySpecific.flipkart.wellnessCenter.controller.Controller;
import companySpecific.flipkart.wellnessCenter.managers.EventManager;
import companySpecific.flipkart.wellnessCenter.managers.SessionUserManager;
import companySpecific.flipkart.wellnessCenter.model.Doctor;
import companySpecific.flipkart.wellnessCenter.model.WellnessUser;

public class Main {
    public static void main(String[] args) {
        SessionUserManager userManager = new SessionUserManager();
        EventManager eventManager = new EventManager();
        Controller controller = new Controller(eventManager, userManager);

        //create user
        WellnessUser user = new WellnessUser("prashant", "prashantrmishra@gmail.com");
        controller.createUser(user);

        Doctor dr = new Doctor("jas bhatia", "jas@bhatia.com");
        controller.createDr(dr);

        controller.getUsers();
        controller.seeListOfAvailableDr();
        
        LocalTime startTime = LocalTime.parse("09:30", DateTimeFormatter.ofPattern("HH:mm"));
        controller.bookEvent(dr, user, "OneOnOneSession",startTime);
        controller.availableEvents();
        


    }
}
