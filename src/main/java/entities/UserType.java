package entities;
/**
 * This enumerates the two different types of user: A user can be either a buyer or a seller.
 */
public enum UserType {
    /**
     * If the user is trying to buy or otherwise obtain food from a seller, then they are a buyer.
     */
    BUYER,
    /**
     * If the user is trying to sell or otherwise give food to a buyer, then they are a seller.
     */
    SELLER
}
