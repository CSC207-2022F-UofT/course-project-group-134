package selling_use_case;

//import entities.DiningHallTypes;

public class SellingInteractor implements SellingInputBoundary {

    private SellingOutputBoundary presenter;
    private SellingDataAccessInterface sellingGateway;

    public SellingInteractor(SellingOutputBoundary presenter, SellingDataAccessInterface sellingGateway){
        this.presenter = presenter;
        this.sellingGateway = sellingGateway;
    }

    public void accept(int orderNumber, String sellerUsername, String sellerEmail, String buyerUsername,
                       String buyerEmail){
        if (!this.sellingGateway.orderExistsById(orderNumber)){
            this.presenter.prepareFailView("Order does not exist");
        }
        else if (!this.sellingGateway.getOrderStatus(orderNumber).equals("Ordered")){
            this.presenter.prepareFailView("Order has already been taken up by another seller");
        }

        this.sellingGateway.updateOrder(orderNumber, "Accepted", sellerUsername);
        this.presenter.prepareSuccessView();
    }

}
