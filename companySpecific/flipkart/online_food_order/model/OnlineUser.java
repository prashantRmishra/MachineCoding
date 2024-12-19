package companySpecific.flipkart.online_food_order.model;

import companySpecific.flipkart.online_food_order.UniqueIdGenerate;

public class OnlineUser {
    private String userId;
    private String userName;
    public OnlineUser(String userName){
        this.userName = userName;
        this.userId = "User-"+UniqueIdGenerate.getId();
    }
    public String getUserId() {
        return userId;
    }
    public String getUserName() {
        return userName;
    }

}
