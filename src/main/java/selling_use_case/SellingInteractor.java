package selling_use_case;

//import entities.DiningHallTypes;

import entities.UserType;
import user_access_use_case.SignUpDsRequestModel;
import user_access_use_case.SignUpResponseModel;

public class SellingInteractor implements SellingInputBoundary {

    final SellingDsGateway sellingDsGateway;

    final SellingPresenter sellingPresenter;

    public SellingInteractor(SellingPresenter presenter, SellingDsGateway sellingDsGateway){
        this.sellingDsGateway = sellingDsGateway;
        this.sellingPresenter = presenter;
    }
    // TODO: we should ensure selling don't take on another order while having one unfulfilled
    public SellingResponseModel accept(SellingRequestModel requestModel){
        int orderNumber = requestModel.getOrder().getOrderNumber();
        String sellerUsername = requestModel.getSeller().getUsername();
        if (!this.sellingDsGateway.orderExistsById(orderNumber)){
            this.sellingPresenter.prepareFailView("Order does not exist");
        }
        else if (!this.sellingDsGateway.getOrderStatus(orderNumber).equals("Ordered")){
            this.sellingPresenter.prepareFailView("Order has already been taken up by another seller");
        }

        SellingDsRequestModel sellingDsModel;

        sellingDsGateway.updateOrder(orderNumber, "Accepted", sellerUsername);;

        SellingResponseModel sellingResponseModel = new SellingResponseModel(requestModel.getOrder());
        return sellingPresenter.prepareSuccessView(sellingResponseModel);

    }

}
