import com.google.gson.JsonElement;

public class ProductResponse {

    private StatusResponse status;
    private String message;
    private JsonElement products;

    public ProductResponse(StatusResponse status) {
        this.status = status;
    }

    public ProductResponse(StatusResponse status, String message) {
        this(status);
        this.message = message;
    }

    public ProductResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.products = data;
    }

}
