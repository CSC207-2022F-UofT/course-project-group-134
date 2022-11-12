package order_use_case;

public interface OrderOutputBoundary {
    OrderResponseModel prepareSuccessView(OrderResponseModel response);

    OrderResponseModel prepareFailView(String error);
}
