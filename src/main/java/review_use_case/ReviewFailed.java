package review_use_case;

/**
 * ReviewFailed exception is an exception that gets raised when
 * the review is unsuccessful at posting.
 */
public class ReviewFailed extends RuntimeException{
    public ReviewFailed(String error){
        super(error);
    }
}
