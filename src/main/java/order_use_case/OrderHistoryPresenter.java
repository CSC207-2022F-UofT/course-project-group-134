package order_use_case;

import java.util.ArrayList;

public class OrderHistoryPresenter {

    // TODO: attributes (should  be the same as ResponseModel)

    // TODO: make a method to return an object of type OrderHistoryResponseModel

    private Integer orderID;

    private String sellerName;

    private String sellerEmail;

    private String residence;

    private String orderStatus;

    private Double totalPrice;
    private String[] foodItems;
    private OrderHistoryResponseModel responseModel;

    public OrderHistoryPresenter(Integer orderID, String sellerName, String sellerEmail, String residence,
                                 String orderStatus, ArrayList<String> foodItems){
        this.orderID = orderID;
        this.sellerEmail = sellerEmail;
        this.sellerName = sellerName;
        this.residence = residence;
        this.orderStatus = orderStatus;
        this.foodItems = foodItems.toArray(String[]::new);
        this.totalPrice = 0.0;

        OrderHistoryResponseModel orderModel = new OrderHistoryResponseModel(this.orderID, this.sellerName,
                this.sellerEmail, this.residence, this.orderStatus, foodItems);
        // orderModel.
        this.responseModel = orderModel;
    }

    public OrderHistoryPresenter(OrderHistoryRequestModel requestModel){

    }


    public OrderHistoryResponseModel getResponseModel() {
        return responseModel;
    }

}
