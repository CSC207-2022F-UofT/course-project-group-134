package chat_use_case;
import chat_use_case.*;
import chat_use_case.models.*;
import chat_use_case.boundaries.*;
import entities.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;



import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ChatGatewayTest {

    private ChatInputBoundary interactor;
    private ChatDsGateway gateway;
    private UserFactory userFactory;

    private Buyer buyer;

    private Seller seller;

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

        buyer = userFactory.createBuyer(UserType.BUYER, "Benjamin Liu","ASDFGHJKL",
                "liubenj5@mail.utoronto.ca");
        seller = userFactory.createSeller(UserType.SELLER, "Lenjamin Biu", "LKJHGFDSA",
                new MealPlan("NEW_COLLEGE", 100.0), "biulenj5@mail.utoronto.ca");
    }

    @AfterEach
    void tearDown() {
        new File("./src/main/java/data_storage/chat_store/Chat_biulenj5_liubenj5").delete();
    }

    @Test
    void createChatTest() throws IOException {
        gateway.createChat(new ChatCreationRequestModel(buyer, seller));
        boolean hasThisChat = false;

        //This checks if the chat exists in the chats instance attribute of the gateway
        for (Chat chat : gateway.chats){
            if (chat.getUsers()[0] == buyer && chat.getUsers()[1] == seller){
                hasThisChat = true;
            }
        }


        // This checks if the file has been created
        File f = new File("./src/main/java/data_storage/chat_store/Chat_biulenj5_liubenj5");
        if(!(f.exists() && !f.isDirectory())) {
            hasThisChat = false;
        }

        assert hasThisChat;
    }

    @Test
    void sendMessageTest() throws IOException {
        gateway.createChat(new ChatCreationRequestModel(buyer, seller));
        assert gateway.getMessageList(new ChatLogRequestModel(buyer, seller)).doesChatExist();

        gateway.sendMessage(new ChatSendMessageModel(buyer, seller,
                "Obtuse, rubber goose, green moose, apple juice, "));
        gateway.sendMessage(new ChatSendMessageModel(buyer, seller,
                "Green snake, birthday cake, large fries, chocolate shake "));

        assert Objects.equals(gateway.getMessageList(new ChatLogRequestModel(buyer, seller)).getChatMessages().get(0)
                .getContents(), "Obtuse, rubber goose, green moose, apple juice, ");
        assert Objects.equals(gateway.getMessageList(new ChatLogRequestModel(buyer, seller)).getChatMessages()
                .get(1).getContents(), "Green snake, birthday cake, large fries, chocolate shake ");

        /*testing if reversing the order of seller and buyer breaks something (it should be the same as if the
        orientation were switched)*/
        gateway.sendMessage(new ChatSendMessageModel(seller, buyer,
                "Obtuse, rubber goose, green moose, apple juice, "));
        gateway.sendMessage(new ChatSendMessageModel(seller, buyer,
                "Green snake, birthday cake, large fries, chocolate shake "));

        assert Objects.equals(gateway.getMessageList(new ChatLogRequestModel(buyer, seller)).getChatMessages().
                get(2).getContents(), "Obtuse, rubber goose, green moose, apple juice, ");
        assert Objects.equals(gateway.getMessageList(new ChatLogRequestModel(buyer, seller)).getChatMessages().
                get(3).getContents(), "Green snake, birthday cake, large fries, chocolate shake ");

        /*testing if reversing the order of seller and buyer breaks something (it should be the same as if the
        orientation were switched)*/
        assert Objects.equals(gateway.getMessageList(new ChatLogRequestModel(seller, buyer)).getChatMessages().
                get(2).getContents(), "Obtuse, rubber goose, green moose, apple juice, ");
        assert Objects.equals(gateway.getMessageList(new ChatLogRequestModel(seller, buyer)).getChatMessages().
                get(3).getContents(), "Green snake, birthday cake, large fries, chocolate shake ");
    }


}