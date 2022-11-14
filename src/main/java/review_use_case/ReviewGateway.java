package review_use_case;

import entities.Review;
import entities.ReviewFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReviewGateway implements ReviewDsGateway {
    private File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, ReviewDsRequestModel> reviews = new HashMap<>();

    public ReviewGateway(String csvPath) throws IOException {
        this.csvFile = new File(csvPath);

        if (!csvFile.exists()){
            csvFile.createNewFile();
        }

        headers.put("review", 0);
        headers.put("rating", 1);
        headers.put("username", 2);
        headers.put("userType", 3);
        headers.put("dininghall", 4);

        if (csvFile.length() == 0){
            save();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            reader.readLine(); // skip header (review, rating, username, userType, dininghall)

            String row;
            while ((row = reader.readLine()) != null){
                String[] col = row.split(",");

                String review = String.valueOf(col[headers.get("review")]);
                int rating = Integer.valueOf(col[headers.get("rating")]);
                String username = String.valueOf(col[headers.get("username")]);
                String userType = String.valueOf(col[headers.get("userType")]);
                String dininghall = String.valueOf(col[headers.get("dininghall")]);

                ReviewDsRequestModel user = new ReviewDsRequestModel(review, rating, username, userType, dininghall);
                reviews.put(review, user);
            }

            reader.close();
        }
    }

    // If file is empty or does not exist
    public void save() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));
        writer.write("review,rating,username,userType,dininghall");
        writer.newLine();
        writer.close();
    }

    public Review readReview(String review, int ratings, ReviewFactory reviewFactory) {
        return null;
    }

    //@edu.umd.cs.findbugs.annotations.SuppressFBWarnings("DM_DEFAULT_ENCODING")
    public void save(ReviewDsRequestModel newReview) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, true));

        String toWrite = newReview.getReviewString() + "," + newReview.getRatings() + "," + newReview.getUsername() +  ","
                + newReview.getReviewType() + "," + newReview.getDininghall();

        writer.write(toWrite);
        writer.newLine();
        writer.close();

        reviews.put(newReview.getReviewString(), newReview);
    }
}
