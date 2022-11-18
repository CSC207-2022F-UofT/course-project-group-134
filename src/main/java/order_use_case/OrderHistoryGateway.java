package order_use_case;

import entities.Order;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderHistoryGateway {

    private ArrayList<Order> orderList;

    public OrderHistoryGateway(){
        // TODO: initialize orderList
    }

    // TODO: rearrange to prevent this code from appearing once more in the constructor
    public static double fetchPrice(String foodName, String residence) throws IOException {
        double price = 0.0;
        residence = residence.toUpperCase();
        Map<String, Integer> headers = new LinkedHashMap<>();

        String csvPath = "Dining_Halls/"+ residence + ".csv";

        File csvFile = new File(csvPath);

        headers.put("ItemName", 0);
        headers.put("Ingredients", 1);
        headers.put("???", 2);
        headers.put("Price", 3);

        BufferedReader reader = new BufferedReader(new FileReader(csvFile));

        String row;
        while ((row = reader.readLine()) != null) {
            String[] col = row.split(",");

            String itemName = String.valueOf(col[headers.get("ItemName")]);
            String[] ingredients = String.valueOf(col[headers.get("Ingredients")]).split(";");
            String abc = String.valueOf(col[headers.get("???")]);
            price = Double.parseDouble(String.valueOf(col[headers.get("Price")]));

            if(foodName.equals(itemName)){
                break;
            }

        }

        return price;
    }

    public ArrayList<Order> getAllOrders(){
        return orderList;
    }

}
