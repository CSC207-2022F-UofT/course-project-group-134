package user_access_use_case;

import entities.UserType;

import java.io.IOException;

public class SignUpRequestController {
    SignUpInputBoundary boundary;

    public SignUpRequestController(SignUpInputBoundary inputBoundary){
        this.boundary = inputBoundary;
    }

    public SignUpResponseModel create(String username, String email, String password, String userTypeString,
                                      String residence, String balanceString) throws IOException {
        SignUpRequestModel requestModel;
        UserType userType;
        double balance = 0;
        if (userTypeString.equals(UserType.SELLER.toString())) {
            userType = UserType.SELLER;
            balance = Double.parseDouble(balanceString);
        } else {
            userType = UserType.BUYER;
            residence = "none";
        }
        requestModel = new SignUpRequestModel(username, email, password, userType, residence, balance);
        return boundary.create(requestModel);
    }
}
