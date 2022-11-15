package selling_use_case;

import java.io.File;
import java.io.IOException;

public class SellingGateway implements SellingDsGateway{
    private File csvFile;
    public SellingGateway(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);
    }
    public void addOrder(SellingDsRequestModel data) {

    }

    public void updateOrder(int orderNumber, String orderStatus, String sellerUsername) {

    }

    public boolean orderExistsById(int orderNumber) {
        return false;
    }

    public String getOrderStatus(int orderNumber) {
        return null;
    }

    public void save(SellingDsRequestModel sellingDsModel) {

    }
}
