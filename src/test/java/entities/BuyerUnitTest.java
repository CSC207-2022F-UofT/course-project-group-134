package entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerUnitTest {

    @Test
    void createBuyerTest(){
        UserFactory userFactory = new UserFactory(new BuyerFactory(), new SellerFactory());
        Buyer buyer = userFactory.createBuyer(UserType.BUYER, "lookcook", "1234", "lookcook@mail.utoronto.ca");
        assertEquals("lookcook", buyer.getUsername());
        assertEquals("1234", buyer.getPassword());
        assertEquals("lookcook@mail.utoronto.ca", buyer.getEmail());
        assertEquals(UserType.BUYER, buyer.getUserType());
        assertEquals("Buyer: lookcook", buyer.toString());
    }
}
