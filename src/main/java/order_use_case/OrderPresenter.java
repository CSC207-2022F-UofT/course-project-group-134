package order_use_case;

public class OrderPresenter implements OrderOutputBoundary{

    @Override
    public OrderResponseModel prepareSuccessView(OrderResponseModel orderReponse) {

        return orderReponse;
    }

    @Override
    public OrderResponseModel prepareFailView(String error) {
        throw new OrderFailed(error);
    }
}
