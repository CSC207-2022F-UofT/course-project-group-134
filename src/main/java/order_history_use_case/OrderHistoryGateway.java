package order_history_use_case;

import entities.User;
import entities.UserType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderHistoryGateway {

    private ArrayList<OrderHistoryRequestModel> orderList;

    public OrderHistoryGateway(User user) throws IOException {
        orderList = new ArrayList<>();

        String csvPath = "orders.csv";
        File csvFile = new File(csvPath);


            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");

                int orderID = Integer.parseInt(col[0]);
                String buyerName = String.valueOf(col[1]);
                String buyerEmail = String.valueOf(col[2]);
                String sellerName = String.valueOf(col[3]);
                String sellerEmail = String.valueOf(col[4]);
                String orderResidence = String.valueOf(col[5]);
                String orderStatus = String.valueOf(col[6]);
                String[] foodItems = String.valueOf(col[7]).split(";");

                if(user.getUserType().equals(UserType.BUYER)){
                    if(user.getUsername().equals(buyerName)){
                        OrderHistoryRequestModel newOrder = new OrderHistoryRequestModel(orderID,
                                buyerName, buyerEmail, sellerName, sellerEmail, orderResidence, orderStatus, foodItems);

                        orderList.add(newOrder);
                    }
                }

                else if(user.getUserType().equals(UserType.SELLER)){
                    if(user.getUsername().equals(sellerName)){
                        OrderHistoryRequestModel newOrder = new OrderHistoryRequestModel(orderID,
                                buyerName, buyerEmail, sellerName, sellerEmail, orderResidence, orderStatus, foodItems);

                        orderList.add(newOrder);
                    }
                }

            }

            reader.close();

    }

    public static double fetchPrice(String foodName, String residence) throws IOException {
        double price = 0.0;
        residence = residence.toUpperCase();

        Map<String, Integer> headers = new LinkedHashMap<>();

        String csvPath = "../Dining_Halls/"+ residence + ".csv";   // TODO: check if this is correct
        File csvFile = new File(csvPath);

        headers.put("ItemName", 0);
        headers.put("Allergens", 1);
        headers.put("Ingredients", 2);
        headers.put("Calories", 3);
        headers.put("Price", 4);

        BufferedReader reader = new BufferedReader(new FileReader(csvFile));

        String row;
        while ((row = reader.readLine()) != null) {
            String[] col = row.split(",");

            String itemName = String.valueOf(col[headers.get("ItemName")]);
            price = Double.parseDouble(String.valueOf(col[headers.get("Price")]));

            /*
            String[] allergens = String.valueOf(col[headers.get("Allergens")]).split(";");
            String[] ingredients = String.valueOf(col[headers.get("Ingredients")]).split(";");
            String calories = String.valueOf(col[headers.get("Calories")]);
             */

            if(foodName.equals(itemName)){
                break;
            }

        }

        return price;
    }

    public ArrayList<OrderHistoryRequestModel> getAllOrders(){
        return orderList;
    }


    }
