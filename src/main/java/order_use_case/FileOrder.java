package order_use_case;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileOrder implements OrderDsGateway{
    private final File csvFile;
    private int currentOrderID = 0;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, OrderDsModel> orders = new HashMap<>();

    public FileOrder(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);

        if (!csvFile.exists()){
            csvFile.createNewFile();
        }

        headers.put("orderID", 0);
        headers.put("buyerName", 1);
        headers.put("buyerEmail", 2);
        headers.put("sellerName", 3);
        headers.put("sellerEmail", 4);
        headers.put("residence", 5);
        headers.put("status", 6);
        headers.put("foodItems", 7);

        if (csvFile.length() == 0) {
            save();
        } else {

            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip header

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                int orderID = Integer.parseInt(col[headers.get("orderID")]);
                String buyerName = String.valueOf(col[headers.get("buyerName")]);
                String buyerEmail = String.valueOf(col[headers.get("buyerEmail")]);
                String sellerName = String.valueOf(col[headers.get("sellerName")]);
                String sellerEmail = String.valueOf(col[headers.get("sellerEmail")]);
                String residence = String.valueOf(col[headers.get("residence")]);
                String status = String.valueOf(col[headers.get("status")]);
                String[] foodItems = (String.valueOf(col[headers.get("foodItems")])).split(";");
                OrderDsModel order = new OrderDsModel(orderID, buyerName, buyerEmail, sellerName, sellerEmail, residence, status, foodItems);
                orders.put(orderID, order);
                this.currentOrderID = orderID + 1;
            }

            reader.close();
        }
    }

    @Override
    public void saveOrder(OrderDsRequestModel orderModel) {
        OrderDsModel order = new OrderDsModel(this.currentOrderID, orderModel.getBuyerName(), orderModel.getBuyerEmail(),
                orderModel.getSellerName(), orderModel.getSellerEmail(), orderModel.getResidence(), orderModel.getStatus(), orderModel.getFoodItems());
        orders.put(this.currentOrderID, order);
        this.save();
    }
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (OrderDsModel order : orders.values()) {
                String line = String.format("%d,%s,%s,%s,%s,%s,%s,%s",
                order.getOrderID(), order.getBuyerName(), order.getBuyerEmail(), order.getSellerName(),
                order.getSellerEmail(), order.getResidence(), order.getStatus(), String.join(";",order.getFoodItems()));
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
