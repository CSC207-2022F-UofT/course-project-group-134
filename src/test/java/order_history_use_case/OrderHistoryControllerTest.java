package order_history_use_case;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class OrderHistoryControllerTest {
    private OrderHistoryInputBoundary orderHistoryInteractor;
    private OrderHistoryController orderHistoryController;

    private OrderHistoryGateway OrderHistoryGateway;

    @BeforeEach
    public void setUp() throws IOException {
        /**
         * Sets up the orders.csv file with some hardcoded orders
         */
        String filepath = "src/test/resources/orders.csv";
        File ordersFile = new File(filepath);

        if( !ordersFile.exists() ){
            ordersFile.createNewFile();

            FileWriter ordersWriter = new FileWriter(filepath);
            BufferedWriter writer = new BufferedWriter(ordersWriter);

            writer.write("orderID,buyerName,buyerEmail,sellerName,sellerEmail,residence,status,foodItems,foodQuantity,price");
            writer.newLine();
            writer.write("0,user,user@mail.utoronto.ca,seller1,seller1@mail.utoronto.ca,NEW_COLLEGE,FINISHED,Pepperoni Pizza ;Veggie Burger ,1;2,28.97");
            writer.newLine();
            writer.write("1,usercopy,usercopy@mail.utoronto.ca,seller2,seller2@mail.utoronto.ca,ST_MICHAELS_COLLEGE,SELLER_CONFIRMED,Pepperoni Pizza ;Veggie Burger ,2;2,37.96");
            writer.newLine();
            writer.write("2,user,user@mail.utoronto.ca,seller2,seller2@mail.utoronto.ca,CHESTNUT,BUYER_CONFIRMED,Pepperoni Pizza ;Veggie Burger ,2;5,67.93");
            writer.newLine();
            writer.write("3,user,user@mail.utoronto.ca,seller2,seller2@mail.utoronto.ca,CHESTNUT,FINISHED,Pepperoni Pizza ;Veggie Burger ,2;5,67.93");
            writer.close();
        }

    }

    @Test
    public void testFinishedOrders() throws IOException {
        String buyerUsername = "user";
        String buyerEmail = "user@mail.utoronto.ca";
        String csvPath = "src/test/resources/orders.csv";
        OrderHistoryOutputBoundary orderHistoryOutputBoundary = new OrderHistoryPresenter();
        orderHistoryInteractor = new OrderHistoryInteractor(buyerUsername, buyerEmail, csvPath, orderHistoryOutputBoundary);
        orderHistoryController = new OrderHistoryController(buyerUsername, buyerEmail, orderHistoryInteractor);

        List<String[]> finishedOrders = orderHistoryController.returnFinishedOrders();
        // System.out.println(Arrays.deepToString(finishedOrders.get(0)));
        String[] order1 = finishedOrders.get(0);

        Assertions.assertEquals(2,finishedOrders.size());
        Assertions.assertEquals(9, finishedOrders.get(0).length);

        Assertions.assertEquals("0", order1[0]);
        Assertions.assertEquals("user", order1[1]);
        Assertions.assertEquals("user@mail.utoronto.ca", order1[2]);
        Assertions.assertEquals("seller1", order1[3]);
        Assertions.assertEquals("seller1@mail.utoronto.ca", order1[4]);
        Assertions.assertEquals("NEW_COLLEGE", order1[5]);
        Assertions.assertEquals("FINISHED", order1[6]);
        Assertions.assertEquals("Pepperoni Pizza (1), Veggie Burger (2)", order1[7]);
        Assertions.assertEquals("28.97", order1[8]);
    }

    @Test
    public void testCurrentOrders() throws IOException {
        String buyerUsername = "user";
        String buyerEmail = "user@mail.utoronto.ca";
        String csvPath = "src/test/resources/orders.csv";

        OrderHistoryOutputBoundary orderHistoryOutputBoundary = new OrderHistoryPresenter();
        orderHistoryInteractor = new OrderHistoryInteractor(buyerUsername, buyerEmail, csvPath, orderHistoryOutputBoundary);
        orderHistoryController = new OrderHistoryController(buyerUsername, buyerEmail, orderHistoryInteractor);

        List<String[]> currentOrders = orderHistoryController.returnCurrentOrders();
        // System.out.println(Arrays.deepToString(currentOrders.get(0)));
        String[] order1 = currentOrders.get(0);


        Assertions.assertEquals(1, currentOrders.size());
        Assertions.assertEquals(9, currentOrders.get(0).length);

        Assertions.assertEquals("2", order1[0]);
        Assertions.assertEquals("user", order1[1]);
        Assertions.assertEquals("user@mail.utoronto.ca", order1[2]);
        Assertions.assertEquals("seller2", order1[3]);
        Assertions.assertEquals("seller2@mail.utoronto.ca", order1[4]);
        Assertions.assertEquals("CHESTNUT", order1[5]);
        Assertions.assertEquals("BUYER_CONFIRMED", order1[6]);
        Assertions.assertEquals("Pepperoni Pizza (2), Veggie Burger (5)", order1[7]);
        Assertions.assertEquals("67.93", order1[8]);
    }


    @AfterEach
    void tearDown() {
        new File("./src/test/resources/orders.csv").delete();
    }

}

