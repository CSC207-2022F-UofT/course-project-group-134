package review_use_case;

import entities.ResidenceType;
import entities.Review;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// Use case layer

/**
 * The gateway for the review use case. The gateway stores new review
 * information and queries existing review information. The database
 * is implemented by storing data in a CSV file.
 */
public class ReviewGateway implements ReviewDsGateway {
    private File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final List<ReviewDsRequestModel> reviews = new ArrayList<>();

    /**
     * ReviewGateway constructor that uses the csvPath provided
     * to be the location for the CSV file for the gateway.
     * @param csvPath
     * @throws IOException
     */
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
            reader.readLine();

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

    /**
     * This method gets called when the CSV file is empty or does not exist.
     * The save method writes all the information in the accounts map to the empty csv file.
     * @throws IOException
     */
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

    /**
     * This method collects all the reviews of a single food item from its corresponding dining hall.
     * Each review contains the reviewer's username, numerical rating (on a scale from 1-5), and their
     * written post.
     * @param itemName
     * @param diningHall
     * @return
     */
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

    /**
     * If a user wants to re-review an item after they bought a food item more than one time, this method
     * will replace the user's old review with the most up-to-date one.
     * @param newReview
     * @throws IOException
     */
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
