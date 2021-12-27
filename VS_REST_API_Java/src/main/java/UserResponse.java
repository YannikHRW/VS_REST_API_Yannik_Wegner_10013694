import com.google.gson.JsonElement;

public class UserResponse{

    private StatusResponse status;
    private String message;
    private JsonElement users;

    public UserResponse(StatusResponse status) {
        this.status = status;
    }

    public UserResponse(StatusResponse status, String message) {
        this(status);
        this.message = message;
    }

    public UserResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.users = data;
    }
}
