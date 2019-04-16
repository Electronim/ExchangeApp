package auth;

public interface Authenticable {
    public String getUsername();
    public String getPasswordHash();
}
