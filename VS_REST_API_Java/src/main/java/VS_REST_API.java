import com.google.gson.Gson;

import java.io.File;
import java.sql.SQLException;
import java.util.Collection;

import static spark.Spark.*;

public class VS_REST_API {

    public static void main(String[] args) throws SQLException {

        File file = new File("VS_REST_API.db");
        String path = file.getAbsolutePath();
        ConnectionDatabase.createConnection("jdbc:sqlite:/" + path);

        // users

        UserService userService = new UserService();

        post("/users", (request, response) -> {
            response.type("application/json");
            boolean valid = userService.addEntity(new Gson().fromJson(request.body(), User.class));
            if (valid){
                response.status(201);
                return new Gson().toJson(new UserResponse(StatusResponse.CREATED_201));
            } else {
                response.status(400);
                return new Gson().toJson(
                        new UserResponse(StatusResponse.BAD_REQUEST_400, new Gson()
                                .toJson("Wrong definition")));
            }
        });

        get("/users", (request, response) -> {
            response.type("application/json");

            Collection<Entity> userList = userService.getEntities();

            if (!userList.isEmpty()) {
                return new Gson().toJson(
                        new UserResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(userList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new UserResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("No User in List")));
            }
        });

        get("/users/:id", (request, response) -> {
            response.type("application/json");

            Collection<Entity> userList = userService.getEntity(request.params(":id"));

            if (!userList.isEmpty()) {
                return new Gson().toJson(
                        new UserResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(userList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new UserResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("User not found")));
            }
        });

        put("/users/:id", (request, response) -> {
            response.type("application/json");
            User toEdit = new Gson().fromJson(request.body(), User.class);
            Entity editedUser = userService.editEntity(toEdit, request.params(":id"));

            if (editedUser != null) {
                return new Gson().toJson(
                        new UserResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(editedUser)));
            } else {
                return new Gson().toJson(
                        new UserResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("User not found or error in edit")));
            }
        });

        delete("/users", (request, response) -> {
            response.type("application/json");
            userService.deleteEntities();
            return new Gson().toJson(
                    new UserResponse(StatusResponse.OK_200, "All users deleted"));
        });

        delete("/users/:id", (request, response) -> {
            response.type("application/json");
            userService.deleteEntity(request.params(":id"));
            return new Gson().toJson(
                    new UserResponse(StatusResponse.OK_200, "User deleted"));
        });


        // products

        ProductService productService = new ProductService();

        post("/products", (request, response) -> {
            response.type("application/json");
            boolean valid = productService.addEntity(new Gson().fromJson(request.body(), Product.class));
            if (valid) {
                response.status(201);
                return new Gson().toJson(new ProductResponse(StatusResponse.CREATED_201));
            } else {
                response.status(400);
                return new Gson().toJson(
                        new ProductResponse(StatusResponse.BAD_REQUEST_400, new Gson()
                                .toJson("Wrong definition")));
            }

        });

        get("/products", (request, response) -> {
            response.type("application/json");

            Collection<Entity> productList = productService.getEntities();

            if (!productList.isEmpty()) {
                return new Gson().toJson(
                        new ProductResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(productList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new ProductResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("No Product in List")));
            }
        });

        get("/products/:id", (request, response) -> {
            response.type("application/json");

            Collection<Entity> productList = productService.getEntity(request.params(":id"));

            if (!productList.isEmpty()) {
                return new Gson().toJson(
                        new ProductResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(productList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new ProductResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("User not found")));
            }
        });

        put("/products/:id", (request, response) -> {
            response.type("application/json");
            Product toEdit = new Gson().fromJson(request.body(), Product.class);
            Entity editedProduct = productService.editEntity(toEdit, request.params(":id"));

            if (editedProduct != null) {
                return new Gson().toJson(
                        new ProductResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(editedProduct)));
            } else {
                return new Gson().toJson(
                        new ProductResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("Product not found or error in edit")));
            }
        });

        delete("/products/:id", (request, response) -> {
            response.type("application/json");
            productService.deleteEntity(request.params(":id"));
            return new Gson().toJson(
                    new ProductResponse(StatusResponse.OK_200, "Product deleted"));
        });


        // contact-requests

        ContactRequestService contactRequestService = new ContactRequestService();

        post("/contact-requests", (request, response) -> {
            response.type("application/json");
            boolean valid = contactRequestService.addEntity(new Gson().fromJson(request.body(), ContactRequest.class));
            if (valid){
                response.status(201);
                return new Gson().toJson(new ContactRequestResponse(StatusResponse.CREATED_201));
            } else {
                response.status(400);
                return new Gson().toJson(
                        new ContactRequestResponse(StatusResponse.BAD_REQUEST_400, new Gson()
                                .toJson("Wrong definition")));
            }

        });

        get("/contact-requests", (request, response) -> {
            response.type("application/json");

            Collection<Entity> contactRequestList = contactRequestService.getEntities();

            if (!contactRequestList.isEmpty()) {
                return new Gson().toJson(
                        new ContactRequestResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(contactRequestList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new ContactRequestResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("No Contact-Request in List")));
            }
        });

        get("/contact-requests/:id", (request, response) -> {
            response.type("application/json");

            Collection<Entity> contactRequestList = contactRequestService.getEntity(request.params(":id"));

            if (!contactRequestList.isEmpty()) {
                return new Gson().toJson(
                        new ContactRequestResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(contactRequestList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new ContactRequestResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("Contact-Request not found")));
            }

        });

        put("/contact-requests/:id", (request, response) -> {
            response.type("application/json");
            ContactRequest toEdit = new Gson().fromJson(request.body(), ContactRequest.class);
            Entity editedContactRequest = contactRequestService.editEntity(toEdit, request.params(":id"));

            if (editedContactRequest != null) {
                return new Gson().toJson(
                        new ContactRequestResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(editedContactRequest)));
            } else {
                return new Gson().toJson(
                        new ContactRequestResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("Contact-request not found or error in edit")));
            }
        });

        delete("/contact-requests", (request, response) -> {
            response.type("application/json");
            contactRequestService.deleteEntities();
            return new Gson().toJson(
                    new ContactRequestResponse(StatusResponse.OK_200, "All contact-requests deleted"));
        });

        delete("/contact-requests/:id", (request, response) -> {
            response.type("application/json");
            contactRequestService.deleteEntity(request.params(":id"));
            return new Gson().toJson(
                    new ContactRequestResponse(StatusResponse.OK_200, "Contact-request deleted"));
        });


        // reviews

        ReviewService reviewService = new ReviewService();

        post("/reviews", (request, response) -> {
            response.type("application/json");
            boolean valid = reviewService.addEntity(new Gson().fromJson(request.body(), Review.class));
            if (valid){
                response.status(201);
                return new Gson().toJson(new ReviewResponse(StatusResponse.CREATED_201));
            } else {
                response.status(400);
                return new Gson().toJson(
                        new ReviewResponse(StatusResponse.BAD_REQUEST_400, new Gson()
                                .toJson("Wrong definition")));
            }

        });

        get("/reviews", (request, response) -> {
            response.type("application/json");

            Collection<Entity> reviewList = reviewService.getEntities();

            if (!reviewList.isEmpty()) {
                return new Gson().toJson(
                        new ReviewResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(reviewList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new ReviewResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("No Review in List")));
            }
        });

        get("/reviews/:id", (request, response) -> {
            response.type("application/json");

            Collection<Entity> reviewList = reviewService.getEntity(request.params(":id"));

            if (!reviewList.isEmpty()) {
                return new Gson().toJson(
                        new ReviewResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(reviewList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new ReviewResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("Review not found")));
            }
        });

        put("/reviews/:id", (request, response) -> {
            response.type("application/json");
            Review toEdit = new Gson().fromJson(request.body(), Review.class);
            Entity editedReview = reviewService.editEntity(toEdit, request.params(":id"));

            if (editedReview != null) {
                return new Gson().toJson(
                        new ReviewResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(editedReview)));
            } else {
                return new Gson().toJson(
                        new ReviewResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("Review not found or error in edit")));
            }
        });

        delete("/reviews", (request, response) -> {
            response.type("application/json");
            reviewService.deleteEntities();
            return new Gson().toJson(
                    new ReviewResponse(StatusResponse.OK_200, "All reviews deleted"));
        });

        delete("/reviews/:id", (request, response) -> {
            response.type("application/json");
            reviewService.deleteEntity(request.params(":id"));
            return new Gson().toJson(
                    new ReviewResponse(StatusResponse.OK_200, "Review deleted"));
        });


        // orders

        OrderService orderService = new OrderService();

        post("/orders", (request, response) -> {
            response.type("application/json");
            boolean valid = orderService.addEntity(new Gson().fromJson(request.body(), Order.class));
            if (valid){
                response.status(201);
                return new Gson().toJson(new OrderResponse(StatusResponse.CREATED_201));
            } else {
                response.status(400);
                return new Gson().toJson(
                        new OrderResponse(StatusResponse.BAD_REQUEST_400, new Gson()
                                .toJson("Wrong definition")));
            }

        });

        get("/orders", (request, response) -> {
            response.type("application/json");

            Collection<Entity> orderList = orderService.getEntities();

            if (!orderList.isEmpty()) {
                return new Gson().toJson(
                        new OrderResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(orderList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new OrderResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("No Order in List")));
            }
        });

        get("/orders/:id", (request, response) -> {
            response.type("application/json");

            Collection<Entity> orderList = orderService.getEntity(request.params(":id"));

            if (!orderList.isEmpty()) {
                return new Gson().toJson(
                        new OrderResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(orderList)));
            } else {
                response.status(404);
                return new Gson().toJson(
                        new OrderResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("Order not found")));
            }
        });

        put("/orders/:id", (request, response) -> {
            response.type("application/json");
            Order toEdit = new Gson().fromJson(request.body(), Order.class);
            Entity editedOrder = orderService.editEntity(toEdit, request.params(":id"));

            if (editedOrder != null) {
                return new Gson().toJson(
                        new OrderResponse(StatusResponse.OK_200, new Gson()
                                .toJsonTree(editedOrder)));
            } else {
                return new Gson().toJson(
                        new OrderResponse(StatusResponse.NOT_FOUND_404, new Gson()
                                .toJson("Order not found or error in edit")));
            }
        });

        delete("/orders", (request, response) -> {
            response.type("application/json");
            orderService.deleteEntities();
            return new Gson().toJson(
                    new OrderResponse(StatusResponse.OK_200, "All orders deleted"));
        });

        delete("/orders/:id", (request, response) -> {
            response.type("application/json");
            orderService.deleteEntity(request.params(":id"));
            return new Gson().toJson(
                    new OrderResponse(StatusResponse.OK_200, "Order deleted"));
        });

    }

}
