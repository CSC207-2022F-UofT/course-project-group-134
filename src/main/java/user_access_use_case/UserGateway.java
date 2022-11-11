package user_access_use_case;

import entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.File;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;

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

        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("email", 2);

        if (csvFile.length() == 0) {
            save();

        } else {

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);
                String email = String.valueOf(col[headers.get("email")]);
                UserDsRequestModel user = new UserDsRequestModel(username, password, email);
                accounts.put(email, user);
            }

            reader.close();
        }
    }

    public UserGateway(){
        //TODO: constructor
    }


    // If file is empty or does not exist
    public void save() throws IOException{
    }

    public void save(UserDsRequestModel newUser) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));

        String toWrite = newUser.getEmail() + "," + newUser.getPassword() + "," + newUser.getUsername();
        writer.write(toWrite);
        writer.newLine();
        writer.close();

    }

    public Boolean existsByEmail(String email){
            for(UserDsRequestModel data: accounts.values()){
                if(data.getEmail().equals(email)) {return true;}
        }
            return false;
    }


}
