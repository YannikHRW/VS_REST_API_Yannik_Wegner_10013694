import org.omg.CORBA.UserException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class UserService implements APIService {

    private Collection<Entity> collection = new ArrayList<>();
    private User user;


    @Override
    public boolean addEntity(Entity entity) {

        User user = (User) entity;

        if (user.getUsername() == null || user.getFirstName() == null || user.getLastName() == null || user.getEmail() == null || user.getPassword() == null){
            return false;
        }

        String sql = "insert into users values ('"
                + user.getUsername() + "', '"
                + user.getFirstName() + "', '"
                + user.getLastName() + "', '"
                + user.getEmail() + "', '"
                + user.getPassword() + "');";

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

        String sql = "SELECT rowid, * FROM users";

        try {

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            while (result.next()) {

                int rowid = result.getInt("rowid");
                String username = result.getString("username");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String password = result.getString("password");

                user = new User(rowid, username, firstName, lastName, email, password);
                collection.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return collection;
    }

    @Override
    public Collection<Entity> getEntity(String id) {

        collection.clear();

        try {

            String table = "SELECT rowid, * FROM users";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(table);

            while (result.next()) {

                int rowid = result.getInt("rowid");
                String rowidS = result.getString("rowid");
                String username = result.getString("username");
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String password = result.getString("password");

                if(Objects.equals(id, rowidS) ||
                        Objects.equals(id, username) ||
                        Objects.equals(id, firstName) ||
                        Objects.equals(id, lastName) ||
                        Objects.equals(id, email) ||
                        Objects.equals(id, password)){

                    user = new User(rowid, username, firstName, lastName, email, password);
                    collection.add(user);
                }
            }

        } catch (SQLException e) {
            return null;
        }
        return collection;
    }

    @Override
    public Entity editEntity(Entity entity, String id) throws UserException {

         User user = (User) entity;

        try {

            if (user.getUsername() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE users SET username = '" + user.getUsername() + "' WHERE rowid = '" + id + "'");
            }
            if (user.getFirstName() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE users SET firstName = '" + user.getFirstName() + "' WHERE rowid = '" + id + "'");
            }
            if (user.getLastName() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE users SET lastName = '" + user.getLastName() + "' WHERE rowid = '" + id + "'");
            }
            if (user.getEmail() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE users SET email = '" + user.getEmail() + "' WHERE rowid = '" + id + "'");
            }
            if (user.getPassword() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE users SET password = '" + user.getPassword() + "' WHERE rowid = '" + id + "'");
            }

            String sql = "SELECT rowid, * FROM users WHERE rowid = " + "'" + id + "'";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            int rowid = result.getInt("rowid");
            String username = result.getString("username");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String email = result.getString("email");
            String password = result.getString("password");

            entity = new User(rowid, username, firstName, lastName, email, password);


        } catch (SQLException e) {
            return null;
        }

        return entity;
    }

    @Override
    public void deleteEntity(String id) {

        String sql = "DELETE FROM users WHERE rowid = " + "'" + id + "'";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEntities() {

        String sql = "DELETE FROM users";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
