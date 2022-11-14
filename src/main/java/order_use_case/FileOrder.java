package order_use_case;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import entities.Order;
import entities.Buyer;

public class FileOrder implements OrderDsGateway{
    private final csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, OrderDsResponseModel> orders = new HashMap<>();

    public FileUser(String csvPath) throws IOException {
        csvFile = new File(csvPath);

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
                String[] foodItems = (String.valueOf(col[headers.get("foodItems")])).split(";");
                OrderDsResponseModel order = new OrderDsResponseModel(orderID, buyerName, buyerEmail, sellerName, sellerEmail, residence, foodItems);
                orders.put(orderID, order);
            }

            reader.close();
        }
    }

    public boolean validOrder(Buyer buyer) {

    }

    public void saveOrder(Order order) {

    }
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (OrderDsResponseModel user : accounts.values()) {
                String line = "%s,%s,%s".formatted(
                        user.getName(), user.getPassword(), user.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
