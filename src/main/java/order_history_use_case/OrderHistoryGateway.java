package order_history_use_case;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class OrderHistoryGateway {


    private ArrayList<OrderHistoryResponseModel> orderList;

    public OrderHistoryGateway(String inputUsername, String inputEmail) throws IOException {
        orderList = new ArrayList<>();

        String csvPath = "src/main/java/data_storage/orders.csv";
        File csvFile = new File(csvPath);
        if (!csvFile.exists()){
            csvFile.createNewFile();
        }


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
                Integer[] foodQuantity = Stream.of((String.valueOf(col[8])).split(";")).map(Integer::valueOf).toArray(Integer[]::new);
                double totalPrice = Double.parseDouble(col[9]);

                if(inputUsername.equals(buyerName) & inputEmail.equals(buyerEmail)){
                    OrderHistoryResponseModel newOrder = new OrderHistoryResponseModel(orderID,
                            buyerName, buyerEmail, sellerName, sellerEmail, orderResidence, orderStatus, foodItems,
                            foodQuantity, totalPrice);

                    orderList.add(newOrder);
                }

            }

            reader.close();

    }

    /*
    public static double fetchPrice(String foodName, String residence) throws IOException {
        double price = 0.0;
        residence = residence.toUpperCase();

        Map<String, Integer> headers = new LinkedHashMap<>();

        String csvPath = "./src/main/java/data_storage/Dining_Halls/"+ residence + ".csv";   // TODO: check if this is correct
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


            // String[] allergens = String.valueOf(col[headers.get("Allergens")]).split(";");
            //  String[] ingredients = String.valueOf(col[headers.get("Ingredients")]).split(";");
            // String calories = String.valueOf(col[headers.get("Calories")]);


            if(foodName.equals(itemName)){
                break;
            }

        }

        return price;
    }

    */

    public ArrayList<OrderHistoryResponseModel> getAllOrders(){
        return orderList;
    }
}