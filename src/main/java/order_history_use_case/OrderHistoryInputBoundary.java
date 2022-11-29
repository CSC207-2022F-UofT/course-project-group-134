package order_history_use_case;

import java.util.List;

public interface OrderHistoryInputBoundary {
    List<String[]> returnFinishedOrders();
    List<String[]> returnCurrentOrders();
}
