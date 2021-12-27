import com.google.gson.JsonElement;

public class ContactRequestResponse {

    private StatusResponse status;
    private String message;
    private JsonElement contact_requests;

    public ContactRequestResponse(StatusResponse status) {
        this.status = status;
    }

    public ContactRequestResponse(StatusResponse status, String message) {
        this(status);
        this.message = message;
    }

    public ContactRequestResponse(StatusResponse status, JsonElement data) {
        this.status = status;
        this.contact_requests = data;
    }

}
