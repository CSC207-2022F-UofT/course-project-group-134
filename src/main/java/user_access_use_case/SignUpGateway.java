package user_access_use_case;

import entities.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.File;

import java.util.*;

// Use case layer

/**
 * The gateway for the signup use case. The gateway stores new user
 * information and queries existing user information. The database
 * is implemented by storing data in a CSV file.
 */
public class SignUpGateway implements SignUpDsGateway {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, SignUpDsRequestModel> accounts = new HashMap<>();

    private static final String defaultCSVPath = "./src/main/java/data_storage/users.csv";

    /**
     * SignUpGateway constructor that uses the default CSV path
     * to be the location for the CSV file for the gateway.
     */
    public SignUpGateway() throws IOException {
        this(defaultCSVPath);
    }

    /**
     * SignUpGateway constructor that uses the csvPath provided
     * to be the location for the CSV file for the gateway.
     * @param csvPath the file path provided for the CSV file for the gateway.
     */
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

    /**
     * This method gets called when the CSV file is empty or does not exist. The
     * save method writes all the information in the accounts map to the empty csv file.
     */
    public void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));
        writer.write("email,password,username,userType,mealPlanBalance,residence");
        writer.newLine();

        for (SignUpDsRequestModel requestModel : accounts.values()) {
            String toWrite = requestModel.getEmail() + "," + requestModel.getPassword() + "," + requestModel.getUsername() +  ","
                    + requestModel.getUserType() + "," + requestModel.getMealPlanBalance() + "," + requestModel.getResidence();
            writer.write(toWrite);
            writer.newLine();
        }

        writer.close();
    }

    /**
     * Writes and stores information for a new user account to the CSV file.
     * @param newUser The request model containing information for the new user account.
     */
    public void save(SignUpDsRequestModel newUser) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));

        String toWrite = newUser.getEmail() + "," + newUser.getPassword() + "," + newUser.getUsername() +  ","
                + newUser.getUserType() + "," + newUser.getMealPlanBalance() + "," + newUser.getResidence();

        writer.write(toWrite);
        writer.newLine();
        writer.close();

        accounts.put(newUser.getEmail(), newUser);
    }

    /**
     * Queries the CSV file to check if a user's email matches the provided email.
     * Note that emails are unique for each user.
     * @param email the email being queried for existence in the csv file.
     * @return The boolean returned is True if and only if a user with the email
     * provided exists in the csv file.
     */
    public Boolean existsByEmail(String email){
        for (SignUpDsRequestModel data: accounts.values()) {
            if (data.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a user based off of an email query.
     * @param email The email of the user to return.
     * @param userFactory The userFactory which creates users.
     * @return Returns either a Buyer or Seller corresponding to the user email if it exists.
     * Otherwise, returns null.
     */
    public User readUser(String email, UserFactory userFactory) {
        for (SignUpDsRequestModel data : accounts.values()) {
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

    /**
     * Returns a user based off of an email query. The UserFactory used is the default one.
     * @param email The email of the user to return.
     * @return Returns either a Buyer or Seller corresponding to the user email if it exists.
     * Otherwise, returns null.
     */
    public User readUser(String email){
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        UserFactory userFactory = new UserFactory(buyerFactory, sellerFactory);
        return readUser(email,userFactory);
    }

    /**
     * Returns a request model based off of an email query if it exists.
     * @param email The email of the user to return the request model of.
     * @return Returns the request model corresponding to the user with the email if it exists.
     * Otherwise, returns null.
     */
    public SignUpDsRequestModel getRequestModelFromEmail(String email) {
        for (SignUpDsRequestModel data : accounts.values()) {
            if (data.getEmail().equals(email)) {
                return data;
            }
        }
        return null;
    }

    /**
     * Modifies the CSV file to subtract a price from the sellers balance.
     * @param sellerEmail The seller email to subtract price from.
     * @param price The price to subtract from the sellers balance.
     */
    public void subtractPrice(String sellerEmail, double price) throws IOException {
        this.csvFile.delete();
        double currentPrice = accounts.get(sellerEmail).getMealPlanBalance();
        double newPrice = currentPrice - price;
        accounts.get(sellerEmail).setMealPlanBalance(newPrice);
        this.save();
    }
}
