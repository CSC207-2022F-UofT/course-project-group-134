package order_history_use_case;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class OrderHistoryGateway {


    private List<OrderHistoryResponseModel> orderList;

    /**
     *
     * @param inputUsername The username of the user whose details we wish to fetch
     * @param inputEmail The email of the user whose details we wish to fetch
     * @throws IOException if the file containing the orders list is not found
     */

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

            if(inputUsername.equals(buyerName) && inputEmail.equals(buyerEmail)){
                OrderHistoryResponseModel newOrder = new OrderHistoryResponseModel(orderID,
                        buyerName, buyerEmail, sellerName, sellerEmail, orderResidence, orderStatus, foodItems,
                        foodQuantity, totalPrice);
                System.out.println("input email = " + inputEmail);
                System.out.println("buyer email = " + buyerEmail);
                orderList.add(newOrder);
            }

        }

        reader.close();
    }

    /**\
     *
     * @return Returns the list of all orders that were fetched by the constructor
     */
    public List<OrderHistoryResponseModel> getAllOrders(){

        return orderList;
    }
}