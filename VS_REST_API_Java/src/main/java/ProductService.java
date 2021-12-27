import org.omg.CORBA.UserException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class ProductService implements APIService{

    private Collection<Entity> collection = new ArrayList<>();
    private Product product;


    @Override
    public boolean addEntity(Entity entity) {

        Product product = (Product) entity;

        if (product.getName() == null || product.getPrice() == 0 || product.getBrand() == null || product.getSince() == 0 || product.getUrl() == null || product.getId() == 0){
            return false;
        }

        String sql = "insert into products values ('" + product.getName() + "', '"
                + product.getPrice() + "', '"
                + product.getBrand() + "', '"
                + product.getSince() + "', '"
                + product.getUrl() + "', '"
                + product.getId() + "');";

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

        String sql = "SELECT * FROM products";

        try {

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            while (result.next()) {

                String name = result.getString("name");
                double price = result.getDouble("price");
                String brand = result.getString("brand");
                int since = result.getInt("since");
                String url = result.getString("url");
                int id = result.getInt("id");

                product = new Product(name, price, brand, since, url, id);
                collection.add(product);
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

            String table = "SELECT * FROM products";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(table);

            while (result.next()) {

                String name = result.getString("name");
                double price = result.getDouble("price");
                String priceS = result.getString("price");
                String brand = result.getString("brand");
                int since = result.getInt("since");
                String sinceS = result.getString("since");
                String url = result.getString("url");
                int id = result.getInt("id");
                String idS = result.getString("id");

                if(Objects.equals(string, name) ||
                        Objects.equals(string, priceS) ||
                        Objects.equals(string, brand) ||
                        Objects.equals(string, sinceS) ||
                        Objects.equals(string, url) ||
                        Objects.equals(string, idS)){

                    product = new Product(name, price, brand, since, url, id);
                    collection.add(product);

                }
            }

        } catch (SQLException e) {
            return null;
        }
        return collection;
    }

    @Override
    public Entity editEntity(Entity entity, String id) throws UserException {

        Product product = (Product) entity;

        try {

            if (product.getName() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE products SET name = '" + product.getName() + "' WHERE id = '" + id + "'");
            }
            if (product.getPrice() != 0) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE products SET price = '" + product.getPrice() + "' WHERE id = '" + id + "'");
            }
            if (product.getBrand() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE products SET brand = '" + product.getBrand() + "' WHERE id = '" + id + "'");
            }
            if (product.getSince() != 0) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE products SET since = '" + product.getSince() + "' WHERE id = '" + id + "'");
            }
            if (product.getUrl() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE products SET url = '" + product.getUrl() + "' WHERE id = '" + id + "'");
            }
            if (product.getId() != 0){
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE products SET id = '" + product.getId() + "' WHERE id = '" + id + "'");
            }

            String sql = "SELECT * FROM products WHERE id = " + "'" + id + "'";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            String name = result.getString("name");
            double price = result.getDouble("price");
            String brand = result.getString("brand");
            int since = result.getInt("since");
            String url = result.getString("url");
            int id_ = result.getInt("id");

            entity = new Product(name, price, brand, since, url, id_);


        } catch (SQLException e) {
            return null;
        }

        return entity;
    }

    @Override
    public void deleteEntity(String id) {

        String sql = "DELETE FROM products WHERE id = " + "'" + id + "'";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEntities() {

        String sql = "DELETE FROM products";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
