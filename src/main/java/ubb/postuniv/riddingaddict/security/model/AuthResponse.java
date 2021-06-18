package ubb.postuniv.riddingaddict.security.model;

import java.util.List;

public class AuthResponse {

    private String token;
    private String username;
    private String firstName;
    private String lastName;
    private List<String> authoritiesList;

    public AuthResponse(String token, String username, String firstName, String lastName, List<String> authoritiesList) {
        this.token = token;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authoritiesList = authoritiesList;
    }

    @Override
    public String toString() {
        return "{\"token\": " + "\"" + token + "\"" +
                ", \"username\": " + "\"" + username + "\"" +
                ", \"firstName\": " + "\"" +firstName + "\"" +
                ", \"lastName\": " + "\"" + lastName + "\"" +
                ", \"authoritiesList\": " + "\"" + authoritiesList + "\"" +
                "}";
    }
}
