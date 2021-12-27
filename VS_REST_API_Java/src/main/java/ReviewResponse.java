import com.google.gson.JsonElement;

public class ReviewResponse {

    private StatusResponse status;
    private String message;
    private JsonElement reviews;

    public ReviewResponse(StatusResponse status) {
        this.status = status;
    }

    public ReviewResponse(StatusResponse status, String message) {
        this(status);
        this.message = message;
    }

    public ReviewResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.reviews = data;
    }

}
