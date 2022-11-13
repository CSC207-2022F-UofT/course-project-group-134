package user_access_use_case;

import entities.UserType;

public class UserResponseModel {
    //Maybe we can make this final?
    private String name;

    public UserResponseModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
