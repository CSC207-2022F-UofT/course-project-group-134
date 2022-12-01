package user_access_use_case;

// Use case layer

/**
 * SignUpResponseModel contains the name of the user signing up
 * when the signup is successful.
 */
public class SignUpResponseModel {

    private final String name;

    public SignUpResponseModel(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
