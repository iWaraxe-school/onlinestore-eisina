import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.sql.SQLException;

public class PlaceOrderHandler implements HttpHandler {
    private static final String PRODUCT_NAME = "pname";

    private static final int PARAM_NAME_IDX = 0;
    private static final int PARAM_VALUE_IDX = 1;


    private static final int HTTP_OK_STATUS = 200;

    private static final String AND_DELIMITER = "&";
    private static final String EQUAL_DELIMITER = "=";

    @Override
    public void handle(HttpExchange t) throws IOException {

        URI uri = t.getRequestURI();
        String response = createResponseFromQueryParams(uri);
        Store onlineStore = Store.getInstance();
        try {
            onlineStore.createOrder(response);
            t.sendResponseHeaders(HTTP_OK_STATUS, response.getBytes().length);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Response: " + response);
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String createResponseFromQueryParams(URI uri) {

        String pName = "";
        String query = uri.toString();
        if (query != null) {
            System.out.println("Query: " + query);
            String[] queryParams = query.split(AND_DELIMITER);
            if (queryParams.length > 0) {
                for (String qParam : queryParams) {
                    String[] param = qParam.split(EQUAL_DELIMITER);
                    if (param.length > 0) {
                        for (int i = 0; i < param.length; i++) {
                            if (PRODUCT_NAME.equalsIgnoreCase(param[PARAM_NAME_IDX])) {
                                pName = param[PARAM_VALUE_IDX];
                            }
                        }
                    }
                }
            }
        }
        return pName;
    }
}

