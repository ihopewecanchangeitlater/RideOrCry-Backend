package gr.uom.RideOrCry.Enums;

public enum UserRole {
    CITIZEN,
    AGENCY;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
