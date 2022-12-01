package user_access_use_case;

import entities.UserType;

import java.io.IOException;

// Use case layer

/**
 * The controller for the signup use case. Takes in information from the
 * signup screen and sends it to the interactor.
 */
public class SignUpRequestController {
    final SignUpInputBoundary boundary;

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
