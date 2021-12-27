import org.omg.CORBA.UserException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ReviewService implements APIService{

    private Collection<Entity> collection = new ArrayList<>();
    private Review review;


    @Override
    public boolean addEntity(Entity entity) {

        Review review = (Review) entity;

        if (review.getRating() == 0 || review.getUsername() == null || review.getText() == null ){
            return false;
        }

        String sql = "insert into reviews values ('"
                + review.getRating() + "', '"
                + review.getUsername() + "', '"
                + review.getText() + "');";

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

        String sql = "SELECT rowid, * FROM reviews";

        try {

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            while (result.next()) {

                int rowid = result.getInt("rowid");
                int rating = result.getInt("rating");
                String username = result.getString("username");
                String text = result.getString("text");

                review = new Review(rowid, rating, username, text);
                collection.add(review);
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

            String table = "SELECT rowid, * FROM reviews";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(table);

            while (result.next()) {

                int rowid = result.getInt("rowid");
                String rowidS = result.getString("rowid");
                int rating = result.getInt("rating");
                String ratingS = result.getString("rating");
                String username = result.getString("username");
                String text = result.getString("text");

                if(Objects.equals(string, rowidS) ||
                        Objects.equals(string, ratingS) ||
                        Objects.equals(string, username) ||
                        Objects.equals(string, text)){

                    review = new Review(rowid, rating, username, text);
                    collection.add(review);

                }
            }

        } catch (SQLException e) {
            return null;
        }
        return collection;
    }

    @Override
    public Entity editEntity(Entity entity, String id) throws UserException {

        Review review = (Review) entity;

        try {

            if (review.getRating() != 0) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE reviews SET rating = '" + review.getRating() + "' WHERE rowid = '" + id + "'");
            }
            if (review.getUsername() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE reviews SET username = '" + review.getUsername() + "' WHERE rowid = '" + id + "'");
            }
            if (review.getText() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE reviews SET text = '" + review.getText() + "' WHERE rowid = '" + id + "'");
            }

            String sql = "SELECT rowid, * FROM reviews WHERE rowid = " + "'" + id + "'";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            int rowid = result.getInt("rowid");
            int rating = result.getInt("rating");
            String username = result.getString("username");
            String text = result.getString("text");

            entity = new Review(rowid, rating, username, text);


        } catch (SQLException e) {
            return null;
        }

        return entity;
    }

    @Override
    public void deleteEntity(String id) {

        String sql = "DELETE FROM reviews WHERE rowid = " + "'" + id + "'";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEntities() {

        String sql = "DELETE FROM reviews";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
