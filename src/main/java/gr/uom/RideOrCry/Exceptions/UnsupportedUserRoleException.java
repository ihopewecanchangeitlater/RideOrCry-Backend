package gr.uom.RideOrCry.Exceptions;

public class UnsupportedUserRoleException extends Exception {
    public UnsupportedUserRoleException() {
        super("Unsupported user role provided");
    }
}
