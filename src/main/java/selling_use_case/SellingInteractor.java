package selling_use_case;

//import entities.DiningHallTypes;

import entities.OrderStatusType;
import order_use_case.OrderDsGateway;

public class SellingInteractor implements SellingInputBoundary {

    final OrderDsGateway orderDsGateway;

    final SellingPresenter sellingPresenter;

    public SellingInteractor(SellingPresenter presenter, OrderDsGateway sellingDsGateway){
        this.orderDsGateway = sellingDsGateway;
        this.sellingPresenter = presenter;
    }

    public void accept(SellingRequestModel requestModel){
        int orderNumber = requestModel.getOrderNumber();
        String sellerEmail = requestModel.getSellerEmail();
        if (!this.orderDsGateway.orderExistsById(orderNumber)){
            this.sellingPresenter.prepareFailView("Order does not exist");
        }
        else if (!this.orderDsGateway.getOrderStatus(orderNumber).equals(OrderStatusType.ORDERED)){
            this.sellingPresenter.prepareFailView("Order has already been taken up by another seller");
        }

        orderDsGateway.updateOrder(orderNumber, OrderStatusType.ACCEPTED, sellerEmail);

        SellingResponseModel sellingResponseModel = new SellingResponseModel(requestModel.getOrderNumber());
        sellingPresenter.prepareSuccessView(sellingResponseModel);

    }

}
