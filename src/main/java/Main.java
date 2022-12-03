import review_use_case.ReviewMain;

/**
 * Main is the class which contains the main method. Creates a welcome screen which
 * prompts the user to either sign up as a new user, or login as an existing user.
 */
public class Main {

    /**
     * The first method that gets called when the program runs. mCreates a welcome screen.
     */
    public static void main(String[] args) throws Exception {
        ReviewMain.create("laciscat", "pizza", "CAMPUS_ONE");
    }
}
