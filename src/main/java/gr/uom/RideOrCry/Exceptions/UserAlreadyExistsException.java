package gr.uom.RideOrCry.Exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("There is already a user with provided credentials");
    }
}
