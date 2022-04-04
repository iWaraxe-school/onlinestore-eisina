import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.SQLException;

public class CategoriesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {

        InputStream is;
        OutputStream os;
        String requestString = "";

        String requestMethod = he.getRequestMethod();
        StringBuilder requestBuffer = new StringBuilder();
        is = he.getRequestBody();
        int rByte;
        while ((rByte = is.read()) != -1) {
            requestBuffer.append((char) rByte);
        }
        is.close();
        if (requestBuffer.length() > 0) {
            requestString = URLDecoder.decode(requestBuffer.toString(), "UTF-8");
        } else {
            requestString = null;
        }

        if (requestMethod.equalsIgnoreCase("GET")) {
            String response = getCategories();
            he.sendResponseHeaders(200, response.length());
            os = he.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        if (requestMethod.equalsIgnoreCase("POST")) {
            try {
                addCategory(requestString);
                he.sendResponseHeaders(200, 0);
            } catch (SQLException e) {
                e.printStackTrace();
                he.sendResponseHeaders(404, 0);
            }
        }
        he.close();
    }


        private String getCategories() {
            DatabaseHelper databaseHelper = new DatabaseHelper();
            String response = "Categories:\n";
            try {
                for (Category category : databaseHelper.getAllCategories()) {
                    response += category.getName() + "\n";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return response;
        }

        private void addCategory(String requestString) throws SQLException {
            DatabaseHelper databaseHelper = new DatabaseHelper();
            databaseHelper.insertCategoryIntoDB(requestString);
        }
    }

