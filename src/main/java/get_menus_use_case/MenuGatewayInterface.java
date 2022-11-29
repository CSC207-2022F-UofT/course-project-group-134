package get_menus_use_case;

import entities.Menu;
import entities.Residence;
import entities.ResidenceType;
import entities.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface MenuGatewayInterface {
    Menu createMenu(ResidenceType type) throws Exception;
    List<Review> getFoodReviews(String foodItem, ResidenceType residenceName) throws IOException;
}
