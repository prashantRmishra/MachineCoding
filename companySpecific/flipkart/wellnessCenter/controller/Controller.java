package companySpecific.flipkart.wellnessCenter.controller;

import java.time.LocalTime;

import companySpecific.flipkart.wellnessCenter.managers.EventManager;
import companySpecific.flipkart.wellnessCenter.managers.SessionUserManager;
import companySpecific.flipkart.wellnessCenter.model.Doctor;
import companySpecific.flipkart.wellnessCenter.model.WellnessUser;

public class Controller {
    private EventManager eventManager;
    private SessionUserManager userManager;
    public Controller(EventManager eManager, SessionUserManager uManager){
        this.eventManager = eManager;
        this.userManager = uManager;
    }
    //create user
    public void createUser(WellnessUser user){
        userManager.addUser(user);
    }

    //create dr
    public void createDr(Doctor dr){
        userManager.addDoctor(dr);
    }
    //see list of available events
    public void availableEvents(){
        eventManager.viewAllAvailableEvents();
    }
    //see list of available dr
    public void seeListOfAvailableDr(){
        userManager.viewAvailableDr();
    }
    //book/create event with a dr
    public void bookEvent(Doctor dr,WellnessUser user,String eventName, LocalTime startSlot){
        eventManager.bookSessionWithDr(dr, user, eventName,startSlot);
    }

    public void getUsers(){
        userManager.getUsers();
    }
}
