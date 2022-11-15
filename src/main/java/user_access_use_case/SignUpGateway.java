package user_access_use_case;

import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.File;

import java.util.*;

public class SignUpGateway implements SignUpDsGateway {

    private File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, SignUpDsRequestModel> accounts = new HashMap<>();

    // this constructor takes in the path to a csvFile and parses all the information
    public SignUpGateway(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);

        if (!csvFile.exists()){
            csvFile.createNewFile();
        }

        headers.put("email", 0);
        headers.put("password", 1);
        headers.put("username", 2);
        headers.put("userType", 3);
        headers.put("balance", 4);
        headers.put("residence", 5);


        if (csvFile.length() == 0) {
            save();

        } else {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip header (email,password,username,mealPlanBalance,residence)

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");

                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);
                String email = String.valueOf(col[headers.get("email")]);
                String userType = String.valueOf(col[headers.get("userType")]);
                double balance = Double.parseDouble(col[headers.get("balance")]);
                String residence = String.valueOf(col[headers.get("residence")]);

                SignUpDsRequestModel user = new SignUpDsRequestModel(username, password, email, userType,
                        balance, residence);
                accounts.put(email, user);
            }

            reader.close();
        }
    }

    // If file is empty or does not exist
    public void save() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));
        writer.write("email,password,username,userType,mealPlanBalance,residence");
        writer.newLine();
        writer.close();
    }

    public void save(SignUpDsRequestModel newUser) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));

        String toWrite = newUser.getEmail() + "," + newUser.getPassword() + "," + newUser.getUsername() +  ","
                + newUser.getUserType() + "," + newUser.getMealPlanBalance() + "," + newUser.getResidence();

        writer.write(toWrite);
        writer.newLine();
        writer.close();

        accounts.put(newUser.getEmail(), newUser);
    }

    public Boolean existsByEmail(String email){
        for (SignUpDsRequestModel data: accounts.values()) {
            if (data.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reads and returns a user based off of an email query.
     * @param email The email of the user to read.
     * @param userFactory The userfactory which creates users.
     * @return Returns either a Buyer or Seller corresponding to the user email if it exists. Otherwise, returns null.
     */
    public User readUser(String email, UserFactory userFactory) {
        for (SignUpDsRequestModel data: accounts.values()) {
            if (data.getEmail().equals(email)) {
                if (data.getUserType().equals(UserType.SELLER.toString())) {
                    MealPlan mealPlan = new MealPlan(data.getResidence(), data.getMealPlanBalance());
                    Seller seller = userFactory.createSeller(UserType.SELLER,
                            data.getUsername(), data.getPassword(), mealPlan, data.getEmail());
                    return seller;
                } else {
                    Buyer buyer = userFactory.createBuyer(UserType.BUYER, data.getUsername(),
                            data.getPassword(), data.getEmail());
                    return buyer;
                }
            }
        }
        return null; // user does not exist by email.
    }
}
