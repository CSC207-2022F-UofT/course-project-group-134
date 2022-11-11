package user_access_use_case;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserGateway implements UserDsGateway {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, UserRequestModel> accounts = new HashMap<>();

    public UserGateway(String csvPath) throws IOException {
        csvFile = new File(csvPath);

        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("email", 2)

        if (csvFile.length() == 0) {
            save();
        }
        else {

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

    /*public UserGateway(){
        //TODO: constructor
    }
    public boolean existsByEmail(){
        Arraylist<User> userList =
    }*/
}
