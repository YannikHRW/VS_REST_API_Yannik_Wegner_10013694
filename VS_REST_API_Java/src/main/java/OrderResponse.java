import com.google.gson.JsonElement;

public class OrderResponse {

    private StatusResponse status;
    private String message;
    private JsonElement orders;

    public OrderResponse(StatusResponse status) {
        this.status = status;
    }

    public OrderResponse(StatusResponse status, String message) {
        this(status);
        this.message = message;
    }

    public OrderResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.orders = data;
    }

}
