public class Product extends Entity{

    private String name;
    private double price;
    private String brand;
    private int since;
    private String url;
    private int id;

    public Product(String name, double price, String brand, int since, String url, int id) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.since = since;
        this.url = url;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
