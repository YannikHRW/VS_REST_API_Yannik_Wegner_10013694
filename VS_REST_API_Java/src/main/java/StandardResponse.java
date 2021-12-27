import com.google.gson.JsonElement;

public class StandardResponse {

    private StatusResponse status;
    private String message;
    private JsonElement users;

    public StandardResponse(StatusResponse status) {
        this.status = status;
    }

    public StandardResponse(StatusResponse status, String message) {
        this(status);
        this.message = message;
    }

    public StandardResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.users = data;
    }

}
