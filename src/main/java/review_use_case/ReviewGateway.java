package review_use_case;

import entities.OrderStatusType;
import entities.ResidenceType;
import entities.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReviewGateway implements ReviewDsGateway {
    private File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final List<ReviewDsRequestModel> reviews = new ArrayList<>();

    public ReviewGateway(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);

        if (!csvFile.exists()){
            csvFile.createNewFile();
        }

        headers.put("review", 0);
        headers.put("rating", 1);
        headers.put("dininghall", 2);
        headers.put("itemName", 3);
        headers.put("username", 4);

        if (csvFile.length() == 0){
            save();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip header (review, rating, dininghall, userType, username)

            String row;
            while ((row = reader.readLine()) != null){
                String[] col = row.split(",");

                String review = String.valueOf(col[headers.get("review")]);
                int rating = Integer.valueOf(col[headers.get("rating")]);
                String username = String.valueOf(col[headers.get("username")]);
                String itemName = String.valueOf(col[headers.get("itemName")]);
                String dininghall = String.valueOf(col[headers.get("dininghall")]);

                ReviewDsRequestModel user = new ReviewDsRequestModel(review, rating, dininghall ,itemName, username);
                reviews.add(user);
            }

            reader.close();
        }
    }

    // If file is empty or does not exist
    public void save() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));
        writer.write("review,rating,dininghall,itemName,username");
        writer.newLine();

        for (ReviewDsRequestModel dsModel : reviews) {
            String toWrite = dsModel.getReviewString() + "," + dsModel.getRatings() + "," + dsModel.getDininghall() +  ","
                    + dsModel.getItemName() + "," + dsModel.getUsername();
            writer.write(toWrite);
            writer.newLine();
        }

        writer.close();
    }

    public List<Review> getReviewsFromName(String itemName, ResidenceType diningHall) {
        List<Review> list = new ArrayList<>();
        for (ReviewDsRequestModel dsModel : reviews) {
            if ((dsModel.getItemName()).equals(itemName) &&
                    dsModel.getDininghall().equals(diningHall.toString())){
                Review review = new Review(dsModel.getReviewString(), dsModel.getRatings(),
                        dsModel.getItemName(), dsModel.getUsername());
                list.add(review);
            }
        }
        return list;
    }

    public void updateReview(ReviewDsRequestModel newReview) throws IOException {
        this.csvFile.delete();
        boolean reviewExists = false;
        ReviewDsRequestModel oldReview = null;
        for (ReviewDsRequestModel dsModel : reviews) {
            if (dsModel.getUsername().equals(newReview.getUsername())
                    && dsModel.getItemName().equals(newReview.getItemName()) &&
                    dsModel.getDininghall().equals(newReview.getDininghall())) {
                reviewExists = true;
                oldReview = dsModel;
            }
        }
        if (reviewExists) {
            reviews.remove(oldReview);
        }
        reviews.add(newReview);
        save();
    }
}
