import org.omg.CORBA.UserException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ContactRequestService implements APIService{

    private Collection<Entity> collection = new ArrayList<>();
    private ContactRequest contactRequest;


    @Override
    public boolean addEntity(Entity entity) {

        ContactRequest contactRequest = (ContactRequest) entity;

        if (contactRequest.getCustomer_number() == null || contactRequest.getSubject() == null || contactRequest.getFirstName() == null || contactRequest.getLastName() == null || contactRequest.getEmail() == null || contactRequest.getCategory() == null || contactRequest.getDescription() == null){
            return false;
        }

        String sql = "insert into contact_requests values ('"
                + contactRequest.getCustomer_number() + "', '"
                + contactRequest.getSubject() + "', '"
                + contactRequest.getFirstName() + "', '"
                + contactRequest.getLastName() + "', '"
                + contactRequest.getEmail() + "', '"
                + contactRequest.getCategory() + "', '"
                + contactRequest.getDescription() + "');";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<Entity> getEntities() {

        collection.clear();

        String sql = "SELECT * FROM contact_requests";

        try {

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            while (result.next()) {

                String customer_number = result.getString("customer_number");
                String subject = result.getString("subject");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String category = result.getString("category");
                String description = result.getString("description");

                contactRequest = new ContactRequest(customer_number, subject, firstName, lastName, email, category, description);
                collection.add(contactRequest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return collection;
    }

    @Override
    public Collection<Entity> getEntity(String string) {

        collection.clear();

        try {

            String table = "SELECT * FROM contact_requests";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(table);

            while (result.next()) {

                String customer_number = result.getString("customer_number");
                String subject = result.getString("subject");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String category = result.getString("category");
                String description = result.getString("description");

                if(Objects.equals(string, customer_number) ||
                        Objects.equals(string, subject) ||
                        Objects.equals(string, firstName) ||
                        Objects.equals(string, lastName) ||
                        Objects.equals(string, email) ||
                        Objects.equals(string, category) ||
                        Objects.equals(string, description)) {

                    contactRequest = new ContactRequest(customer_number, subject, firstName, lastName, email, category, description);
                    collection.add(contactRequest);

                }
            }

        } catch (SQLException e) {
            return null;
        }
        return collection;
    }

    @Override
    public Entity editEntity(Entity entity, String id) throws UserException {

        ContactRequest contactRequest = (ContactRequest) entity;

        try {

            if (contactRequest.getCustomer_number() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE contact_requests SET customer_number = '" + contactRequest.getCustomer_number() + "' WHERE customer_number = '" + id + "'");
            }
            if (contactRequest.getSubject() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE contact_requests SET subject = '" + contactRequest.getSubject() + "' WHERE customer_number = '" + id + "'");
            }
            if (contactRequest.getFirstName() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE contact_requests SET firstName = '" + contactRequest.getFirstName() + "' WHERE customer_number = '" + id + "'");
            }
            if (contactRequest.getLastName() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE contact_requests SET lastName = '" + contactRequest.getLastName() + "' WHERE customer_number = '" + id + "'");
            }
            if (contactRequest.getEmail() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE contact_requests SET email = '" + contactRequest.getEmail() + "' WHERE customer_number = '" + id + "'");
            }
            if (contactRequest.getCategory() != null){
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE contact_requests SET category = '" + contactRequest.getCategory() + "' WHERE customer_number = '" + id + "'");
            }
            if (contactRequest.getDescription() != null){
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE contact_requests SET description = '" + contactRequest.getDescription() + "' WHERE customer_number = '" + id + "'");
            }

            String sql = "SELECT * FROM contact_requests WHERE customer_number = " + "'" + id + "'";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            String customer_number = result.getString("customer_number");
            String subject = result.getString("subject");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String email = result.getString("email");
            String category = result.getString("category");
            String description = result.getString("description");

            entity = new ContactRequest(customer_number, subject, firstName, lastName, email, category, description);


        } catch (SQLException e) {
            return null;
        }

        return entity;
    }

    @Override
    public void deleteEntity(String id) {

        String sql = "DELETE FROM contact_requests WHERE customer_number = " + "'" + id + "'";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEntities() {

        String sql = "DELETE FROM contact_requests";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
