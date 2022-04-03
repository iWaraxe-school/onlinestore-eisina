import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CategoriesHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange he) throws IOException {


//        DatabaseHelper databaseHelper = new DatabaseHelper();
//        String response = "Categories:\n";
//        try {
//            for (Category category : databaseHelper.getAllCategories()) {
//                response += category.getName() + "\n";
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        he.sendResponseHeaders(200, response.length());
//        OutputStream os = he.getResponseBody();
//        os.write(response.toString().getBytes());
//        os.close();

       DatabaseHelper databaseHelper = new DatabaseHelper();
        String response = "Categories:\n";
        InputStream is; //used for reading in the request data
       OutputStream os; //used for writing out the response data
        String requestString = "";
        JSONObject jsonRequest = new JSONObject();
       // String response = "";

        StringBuilder responseBuffer = new StringBuilder(); // put the response text in this buffer to be sent out at the end
        int httpResponseCode = 200; // This is where the HTTP response code to send back to the client should go

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
         response = "Categories:\n";
        try {
            for (Category category : databaseHelper.getAllCategories()) {
                response += category.getName() + "\n";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        he.sendResponseHeaders(200, response.length());
         os = he.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();

         }
        if (requestMethod.equalsIgnoreCase("POST")) {
            //   if (uri.equals("/categories")) {
            JSONObject productData = null;
            try {
               // productData = databaseHelper.insertCategoryIntoDB(jsonRequest);
                 databaseHelper.insertCategoryIntoDB(requestString);
            } catch (SQLException e) {
                e.printStackTrace();
            }

          //  responseBuffer.append(productData.toString());
            responseBuffer.append(requestString);
            // if(Debugger.DEBUG){
          //  System.out.println(productData.toString());
            // }
            //  DatabaseHelper databaseHelper = new DatabaseHelper();

            try {
                for (Category category : databaseHelper.getAllCategories()) {
                    response += category.getName() + "\n";
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            he.sendResponseHeaders(200, response.length());

            os = he.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }

         //   }




                  //  System.out.println("Recieved: " + requestString + " for: " + uri);

            response = responseBuffer.toString();
                System.out.println("Response: " + response);

//
//            Headers h = he.getResponseHeaders();
//            h.add("Content-Type", "application/jsonp; charset=UTF-8");
//            h.add("Access-Control-Allow-Origin","*");
//            h.add("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept");
//            h.add("Access-Control-Allow-Methods","POST, GET, OPTIONS");

//            he.sendResponseHeaders(httpResponseCode, response.length());
//            os = he.getResponseBody();
//            os.write(response.getBytes("UTF-8"));
//            os.flush();
//            os.close();
//            he.close();

    }

    }
