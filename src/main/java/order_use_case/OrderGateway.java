package order_use_case;

import entities.OrderStatusType;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

public class OrderGateway implements OrderDsGateway{
    private final File csvFile;
    private int currentOrderID = 0;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<Integer, OrderDsModel> orders = new HashMap<>();

    /**
     * The constructor of the gateway to the database for orders.
     * If the file does not exist, create a new file.
     * Otherwise, the file is read and stored in the gateway.
     *
     * @param csvPath path to file
     * @throws IOException
     */
    public OrderGateway(String csvPath) throws IOException {
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
        headers.put("foodQuantity", 8);
        headers.put("price", 9);

        if (csvFile.length() == 0) {
            save(); // Does this get run ever?
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
                //I love java
                Integer[] foodQuantity =  Stream.of((String.valueOf(col[headers.get("foodQuantity")])).split(";")).map(Integer::valueOf).toArray(Integer[]::new);
                Double price = Double.valueOf(col[headers.get("price")]);
                OrderDsModel order = new OrderDsModel(orderID, buyerName, buyerEmail, sellerName, sellerEmail, residence, status, foodItems, foodQuantity, price);
                orders.put(orderID, order);
                this.currentOrderID++;
            }

            reader.close();
        }
    }

    /**
     * Saves an order.
     *
     * @param orderModel The model of the order.
     */
    @Override
    public void saveOrder(OrderDsRequestModel orderModel) {
        OrderDsModel order = new OrderDsModel(this.currentOrderID, orderModel.getBuyerName(), orderModel.getBuyerEmail(),
                orderModel.getSellerName(), orderModel.getSellerEmail(), orderModel.getResidence(), orderModel.getStatus(), orderModel.getFoodItems(), orderModel.getFoodQuantity(), orderModel.getPrice());
        orders.put(this.currentOrderID, order);
        this.currentOrderID++;
        this.save();
    }

    /**
     * Saves orders to the file.
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (OrderDsModel order : orders.values()) {
                String line = String.format("%d,%s,%s,%s,%s,%s,%s,%s,%s,%.2f",
                order.getOrderID(), order.getBuyerName(), order.getBuyerEmail(), order.getSellerName(), order.getSellerEmail(), order.getResidence(),
                        order.getStatus(), String.join(";",order.getFoodItems()), String.join(";", Stream.of(order.getFoodQuantity()).map(String::valueOf).toArray(String[]::new)), order.getPrice());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean orderExistsById(int orderNumber){
        return this.orders.containsKey(orderNumber);
    }

    /**
     * Filters orders by a dining hall and "ORDERED" status from <a href="#{@link}">{@link OrderStatusType}</a>.
     *
     * @param sellerResidence   A dining hall
     * @return                  A list of order IDs
     */
    public List<Integer> getUnfulfilledOrders(String sellerResidence) {
        List<Integer> orders = new ArrayList<>();
        for (Map.Entry<Integer, OrderDsModel> entry : this.orders.entrySet()){
            if (entry.getValue().getStatus().equals(OrderStatusType.ORDERED.toString()) &&
                    entry.getValue().getResidence().equals(sellerResidence)){
                orders.add(entry.getKey());
            }
        }
        return orders;
    }

    /**
     * Filters orders by a seller hall and "ORDERED" status from <a href="#{@link}">{@link OrderStatusType}</a>.
     *
     * @param sellerEmail
     * @return A list of order IDs
     */
    public List<Integer> getFinishedOrders(String sellerEmail) {
        List<Integer> finishedOrders = new ArrayList<Integer>();
        for (Map.Entry<Integer, OrderDsModel> entry : this.orders.entrySet()){
            if (entry.getValue().getStatus().equals(OrderStatusType.FINISHED.toString()) &&
            entry.getValue().getSellerEmail().equals(sellerEmail)){
                finishedOrders.add(entry.getKey());
            }
        }
        return finishedOrders;
    }

    /**
     * Get the order associated with the given order ID.
     *
     * @param orderNumber   order ID
     * @return a model representing the order
     */
    public OrderDsModel getOrderInfo(int orderNumber) {
        return orders.get(orderNumber);
    }

    /**
     * Get the status of the order associated with the given order ID.
     *
     * @param orderNumber   order ID
     * @return <a href="#{@link}">{@link OrderStatusType}</a>
     */
    public OrderStatusType getOrderStatus(int orderNumber) {
        OrderDsModel model = getOrderInfo(orderNumber);
        return OrderStatusType.valueOf(model.getStatus());
    }

    /**
     * Set the status of the order associated with the given order ID.
     *
     * @param orderNumber   order ID
     * @param status        status from <a href="#{@link}">{@link OrderStatusType}</a>
     */
    public void setOrderStatus(int orderNumber, OrderStatusType status) {
        this.csvFile.delete();
        orders.get(orderNumber).setStatus(status.toString());
        save();
    }

    /**
     * Update an order's status, seller email, and seller name.
     *
     * @param orderNumber   order ID
     * @param status        status from <a href="#{@link}">{@link OrderStatusType}</a>
     * @param sellerEmail
     * @param sellerName
     */
    public void updateOrder(int orderNumber, OrderStatusType status, String sellerEmail, String sellerName){
        this.csvFile.delete();
        orders.get(orderNumber).setStatus(status.toString());
        orders.get(orderNumber).setSellerName(sellerName);
        orders.get(orderNumber).setSellerEmail(sellerEmail);
        save();
    }

    /**
     * Check if seller has an order5
     * @param sellerEmail
     * @return True if there's an order
     */
    public boolean sellerHasOrder(String sellerEmail){
        for (Map.Entry<Integer, OrderDsModel> entry : this.orders.entrySet()){
            if (entry.getValue().getSellerEmail().equals(sellerEmail) &&
                    !(entry.getValue().getStatus().equals(OrderStatusType.FINISHED.toString()))){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the order IDs associated with a seller.
     *
     * @param sellerEmail
     * @return list of order IDs
     */
    public ArrayList<Integer> getOrderNumbersFromSellerEmail(String sellerEmail) {
        ArrayList<Integer> orderNumbers = new ArrayList<Integer>();
        for (Map.Entry<Integer, OrderDsModel> entry : this.orders.entrySet()) {
            if (entry.getValue().getSellerEmail().equals(sellerEmail) &&
                    !(entry.getValue().getStatus().equals(OrderStatusType.FINISHED.toString()))) {
                orderNumbers.add(entry.getValue().getOrderID());
            }
        }
        return orderNumbers;
    }

    /**
     * Get the price of an order.
     *
     * @param orderNumber
     * @return price
     * @throws DoesNotExistException
     */
    public double getPriceFromOrderNumber(int orderNumber) throws DoesNotExistException {
        //System.out.println("WTF");
        for (Map.Entry<Integer, OrderDsModel> entry : this.orders.entrySet()) {
            if (entry.getValue().getOrderID() == orderNumber) {
                System.out.println("returning " + entry.getValue().getPrice());
                return entry.getValue().getPrice();
            }
        }
        throw new DoesNotExistException("Order does not exist.");
    }
}
