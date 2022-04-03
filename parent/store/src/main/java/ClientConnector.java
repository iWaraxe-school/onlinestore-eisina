import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Scanner;


public class ClientConnector {

    private static final String URL = "http://localhost:8080/categories";

    public String getCategories() throws Exception {
        URL url = new URL(URL);
        String encoding = Base64.getEncoder().encodeToString(("admin:123").getBytes("UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
            return response;
        }
        return null;
    }

    public void sendPOST(String name) throws Exception {
        URL url = new URL(URL);
        String encoding = Base64.getEncoder().encodeToString(("admin:123").getBytes("UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);
        String postData = URLEncoder.encode(name);
        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
        wr.write(postData);
        wr.flush();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            System.out.println("POST was successful.");
        } else if (responseCode == 404) {
            System.out.println("Error occurred. Try again.");
        }
    }

    public String createOrder(String name) throws Exception {
        URL url = new URL("http://localhost:8080/placeOrder&pname=" + name);
        String encoding = Base64.getEncoder().encodeToString(("admin:123").getBytes("UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();
            return response;
        }
        return null;
    }
}