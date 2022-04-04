import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpServerConnector {
    private final String PASSWORD = "123";
    private final String USERNAME = "admin";


    public void connectServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        System.out.println("Internet Shop ");
        Authentication( server.createContext("/categories", new CategoriesHandler()));
        Authentication( server.createContext("/placeOrder", new PlaceOrderHandler()));
        server.setExecutor(null);
        server.start();
    }

        public void Authentication(HttpContext httpContext) {
            httpContext.setAuthenticator(new BasicAuthenticator("Test") {
                @Override
                public boolean checkCredentials(String user, String pwd) {
                    return user.equals(USERNAME) && pwd.equals(PASSWORD);
                }
            });
        }
}
