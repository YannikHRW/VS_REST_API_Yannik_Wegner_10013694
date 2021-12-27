import com.sun.tools.corba.se.idl.constExpr.Or;
import org.omg.CORBA.UserException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class OrderService implements APIService {

    private Collection<Entity> collection = new ArrayList<>();
    private Order order;


    @Override
    public boolean addEntity(Entity entity) {

        Order order = (Order) entity;

        if (order.getPurchaser() == null || order.getProduct() == null || order.getQuantity() == 0 || order.getTotalcosts() == 0 ){
            return false;
        }

        String sql = "insert into orders values ('"
                + order.getPurchaser() + "', '"
                + order.getProduct() + "', '"
                + order.getQuantity() + "', '"
                + order.getTotalcosts() + "');";

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

        String sql = "SELECT rowid, * FROM orders";

        try {

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            while (result.next()) {

                int rowid = result.getInt("rowid");
                String purchaser = result.getString("purchaser");
                String product = result.getString("product");
                int quantity = result.getInt("quantity");
                double totalcosts = result.getDouble("totalcosts");

                order = new Order(rowid, purchaser, product, quantity, totalcosts);
                collection.add(order);
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

            String table = "SELECT rowid, * FROM orders";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(table);

            while (result.next()) {

                int rowid = result.getInt("rowid");
                String rowidS = result.getString("rowid");
                String purchaser = result.getString("purchaser");
                String product = result.getString("product");
                int quantity = result.getInt("quantity");
                String quantityS = result.getString("quantity");
                double totalcosts = result.getDouble("totalcosts");
                String totalcostsS = result.getString("totalcosts");

                if(Objects.equals(string, rowidS) ||
                        Objects.equals(string, purchaser) ||
                        Objects.equals(string, product) ||
                        Objects.equals(string, quantityS) ||
                        Objects.equals(string, totalcostsS)){

                    order = new Order(rowid, purchaser, product, quantity, totalcosts);
                    collection.add(order);

                }
            }

        } catch (SQLException e) {
            return null;
        }
        return collection;
    }

    @Override
    public Entity editEntity(Entity entity, String id) throws UserException {

        Order order = (Order) entity;

        try {

            if (order.getPurchaser() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE orders SET purchaser = '" + order.getPurchaser() + "' WHERE rowid = '" + id + "'");
            }
            if (order.getProduct() != null) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE orders SET product = '" + order.getProduct() + "' WHERE rowid = '" + id + "'");
            }
            if (order.getQuantity() != 0) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE orders SET quantity = '" + order.getQuantity() + "' WHERE rowid = '" + id + "'");
            }
            if (order.getTotalcosts() != 0) {
                ConnectionDatabase.getConnectionToDatabase().executeUpdate("UPDATE orders SET totalcosts = '" + order.getTotalcosts() + "' WHERE rowid = '" + id + "'");
            }


            String sql = "SELECT rowid, * FROM orders WHERE rowid = " + "'" + id + "'";

            ResultSet result = ConnectionDatabase.getConnectionToDatabase().executeQuery(sql);

            int rowid = result.getInt("rowid");
            String purchaser = result.getString("purchaser");
            String product = result.getString("product");
            int quantity = result.getInt("quantity");
            double totalcosts = result.getDouble("totalcosts");

            entity = new Order(rowid, purchaser, product, quantity, totalcosts);


        } catch (SQLException e) {
            return null;
        }

        return entity;
    }

    @Override
    public void deleteEntity(String id) {

        String sql = "DELETE FROM orders WHERE rowid = " + "'" + id + "'";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteEntities() {

        String sql = "DELETE FROM orders";

        try {

            ConnectionDatabase.getConnectionToDatabase().executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
