public class Order extends Entity{

    private int id;
    private String purchaser;
    private String product;
    private int quantity;
    private double totalcosts;

    public Order(int id, String purchaser, String product, int quantity, double totalcosts) {
        this.id = id;
        this.purchaser = purchaser;
        this.product = product;
        this.quantity = quantity;
        this.totalcosts = totalcosts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(String purchaser) {
        this.purchaser = purchaser;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalcosts() {
        return totalcosts;
    }

    public void setTotalcosts(double totalcosts) {
        this.totalcosts = totalcosts;
    }
}
