package user_access_use_case;

public interface UserDsGateway {
    Boolean existsByEmail(String email);

    void save(UserRequestModel requestModel);
}
