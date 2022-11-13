package user_access_use_case;

public class SignUpResponseModel {
    //Maybe we can make this final?
    private String name;

    public SignUpResponseModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
