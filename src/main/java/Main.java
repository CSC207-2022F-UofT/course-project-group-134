import review_use_case.ReviewMain;
import screens.ReviewScreen;
import screens.WelcomeScreen;

public class Main {

    public static void main(String[] args) throws Exception {
        //WelcomeScreen welcomeScreen = new WelcomeScreen();
        ReviewMain.create("tb3","Pepperoni Pizza", "NEW_COLLEGE");
    }
}
