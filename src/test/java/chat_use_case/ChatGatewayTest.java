package chat_use_case;
import chat_use_case.*;
import chat_use_case.models.*;
import chat_use_case.boundaries.*;
import entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import user_access_use_case.*;


import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ChatGatewayTest {

    private ChatInputBoundary interactor;
    private ChatDsBoundary gateway;
    private UserFactory userFactory;

    @BeforeEach
    void setUp() throws Exception {
        try {
            gateway = ChatDsGateway.getInstance();
        } catch (IOException e) {
            throw new RuntimeException("Could not create file.");
        }
        BuyerFactory buyerFactory = new BuyerFactory();
        SellerFactory sellerFactory = new SellerFactory();
        userFactory = new UserFactory(buyerFactory, sellerFactory);
        interactor = new ChatInteractor();

    }

    @AfterEach
    void tearDown() {
        new File("./src/main/java/data_storage/chat_store").delete();
    }
}