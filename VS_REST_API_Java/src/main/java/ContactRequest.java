public class ContactRequest extends Entity{

    private String customer_number;
    private String subject;
    private String firstName;
    private String lastName;
    private String email;
    private String category;
    private String description;

    public ContactRequest(String customer_number, String subject, String firstName, String lastName, String email, String category, String description) {
        this.customer_number = customer_number;
        this.subject = subject;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.category = category;
        this.description = description;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
