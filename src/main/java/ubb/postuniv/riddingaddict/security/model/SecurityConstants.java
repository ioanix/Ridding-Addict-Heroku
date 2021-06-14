package ubb.postuniv.riddingaddict.security.model;

public class SecurityConstants {

    private SecurityConstants() {}

    public static final String SECRET = "SECRET_KEY";
    public static final String ROLE_USER = "[\"ROLE_USER\"]";
    public static final String ROLE_ADMIN = "[\"ROLE_ADMIN\"]";
    public static final long EXPIRATION_TIME = 60; // 60 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/users/register";
}
