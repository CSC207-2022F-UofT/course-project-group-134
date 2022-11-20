package review_use_case;

public class ReviewFailed extends RuntimeException{
    public ReviewFailed(String error){
        super(error);
    }
}
