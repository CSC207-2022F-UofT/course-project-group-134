package user_access_use_case;

import entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.File;

import java.util.*;

public class UserGateway implements UserDsGateway {

    private File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, UserDsRequestModel> accounts = new HashMap<>();

    // this constructor takes in the path to a csvFile and parses all the information
    public UserGateway(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);

        if (!csvFile.exists()){
            csvFile.createNewFile();
        }

        headers.put("email", 0);
        headers.put("password", 1);
        headers.put("username", 2);

        // New code begins
        ArrayList<String> headers_temp = new ArrayList<String>(
                Arrays.asList("email", "password", "username", "user type", "dining halls", "meal plan balance")
        );

        Map<String, Integer> headers_2 = new HashMap<>();
        for(int i = 0; i < headers_temp.size(); i++){
            headers_2.put(headers_temp.get(i), i);
        }
        // New code ends

        if (csvFile.length() == 0) {
            save();

        } else {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip header (email,password,username)

            String row;
            while ((row = reader.readLine()) != null) {
                /*
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);
                String email = String.valueOf(col[headers.get("email")]);
                */

                // New code begins
                String[] col = row.split(",");

                String username = String.valueOf(col[0]);
                String password = String.valueOf(col[1]);
                String email = String.valueOf(col[2]);
                String userType = String.valueOf(col[3]);
                double balance = Double.parseDouble(col[4]);
                String diningHalls = String.valueOf(col[5]);

                // Changing diningHalls into an ArrayList from a String[]
                ArrayList<String> diningHallsList = new ArrayList<>();
                diningHallsList.addAll(Arrays.asList(diningHalls.split("/")));

                // New code ends

                UserDsRequestModel user = new UserDsRequestModel(username, password, email, userType,
                        balance, diningHallsList);
                accounts.put(email, user);
            }

            reader.close();
        }
    }

    // If file is empty or does not exist
    public void save() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));
        writer.write("email,password,username,userType,allowedDiningHalls,mealPlanBalance");
        writer.newLine();
        writer.close();
    }

    public void save(UserDsRequestModel newUser) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));

        String toWrite = newUser.getEmail() + "," + newUser.getPassword() + "," + newUser.getUsername() +  ","
                + newUser.getUserType() + "," + newUser.getMealPlanBalance() + ",";

        String allowedDiningHallsString = String.join("/", newUser.getAllowedDiningHalls());

        toWrite += allowedDiningHallsString;

        // NOTE: There is a slash (/) between each allowed dining hall

        writer.write(toWrite);
        writer.newLine();
        writer.close();

        accounts.put(newUser.getEmail(), newUser);
    }

    public Boolean existsByEmail(String email){
        for (UserDsRequestModel data: accounts.values()) {
            if (data.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

}
