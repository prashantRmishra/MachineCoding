package companySpecific.flipkart.wellnessCenter.model.events;

import companySpecific.flipkart.wellnessCenter.model.Doctor;
import companySpecific.flipkart.wellnessCenter.model.SessionSlot;
import companySpecific.flipkart.wellnessCenter.model.WellnessUser;

public class OneOnOneSession extends WellnessEvent {

    public OneOnOneSession(Doctor dr, WellnessUser user, SessionSlot slot,String name,String dateString) {
        super(dr, user, slot,name,dateString);
    }
    
}
