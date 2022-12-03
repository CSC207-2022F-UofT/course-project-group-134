package entities;

/**
 * This class represents a User. A User is the representation of a person using our app within our service
 * and stores the login information (username, password, and email) of the person.
 *
 * @author Vivian Liu
 */
public abstract class User {
    private String username;
    private String password;

    /**
     * This represents the number of times the user has been reported.
     */
    private int numReported = 0;
    private String[] chats;
    private final String email;
    protected UserType userType;

    /**
     * Constructor for user.
     * This initializes a user with the given username, password, and email. numReported is initialized as zero.
     *
     * @param name User's username.
     * @param password User's password. This is currently represented as plaintext.
     * @param email User's email.
     */
    public User (String name, String password, String email){
        this.username = name;
        this.password = password;
        this.email = email;
    }

    /**
     * Gets the user's username
     * @return The user's username, represented as a string.
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * Gets the user's password
     * @return The user's password, represented as a string.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Sets the user's username to newName.
     * @param newName the new username to set the user's username to.
     */
    public void setUsername(String newName){
        this.username = newName;
    }

    /**
     * Sets the user's password to newPassword
     * @param newPassword the new password to set the user's password to.
     */
    public void setPassword(String newPassword){
        this.password = newPassword;
    }

    /**
     * Gets the user's email
     * @return the user's email, represented as a string.
     */
    public String getEmail() {
        return email;
    }

    public UserType getUserType(){
        return userType;
    }

}
