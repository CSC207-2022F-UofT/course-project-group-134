package order_history_use_case;

import java.util.ArrayList;

public interface OrderHistoryInputBoundary {
    ArrayList<String[]> returnFinishedOrders();
    ArrayList<String[]> returnCurrentOrders();
}
