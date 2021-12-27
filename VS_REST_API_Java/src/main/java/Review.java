public class Review extends Entity{

    private int id;
    private int rating;
    private String username;
    private String text;

    public Review(int id, int rating, String username, String text) {
        this.id = id;
        this.rating = rating;
        this.username = username;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
