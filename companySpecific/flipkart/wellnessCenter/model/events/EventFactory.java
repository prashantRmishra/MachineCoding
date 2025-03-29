package companySpecific.flipkart.wellnessCenter.model.events;

public class EventFactory {
    //GroupTherapySession,LectureSession,OneOnOneSession, SupportGroupSession
    public static WellnessEvent createEvent(EventType e){
        WellnessEvent event = null;
        switch (e.toString()) {
            case "GroupTherapySession":{event = new GroupTherapySession(null, null, null, "GroupTherapySession", null);break;}
            case "LectureSession":{event = new LectureSession(null, null, null, "LectureSession", null);break;}
            case "OneOnOneSession":{event = new OneOnOneSession(null, null, null, "OneOnOneSession", null);break;}
            case "SupportGroupSession":{event  =new SupportGroupSession(null, null, null, "SupportGroupSession", null);break;}
            default: break;
        }
        return event;
    }
}
