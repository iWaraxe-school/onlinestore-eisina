import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;


public class ClientConnector {

    private static final String USER_AGENT = "Safari/605.1.15";

    private static final String GET_URL = "localhost:8080/categories";

    private static final String POST_URL = "https://localhost:9090/SpringMVCExample/home";

    private static final String POST_PARAMS = "userName=Pankaj";

    public String getCategories() throws Exception {
        URL url = new URL ("http://localhost:8080/categories");
        String encoding = Base64.getEncoder().encodeToString(("admin:123").getBytes("UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty  ("Authorization", "Basic " + encoding);


       // System.out.println(connection.getResponseCode() + connection.getResponseMessage());
//        for(int i=1;i<=8;i++)
//        {
//            System.out.println(connection.getHeaderFieldKey(i)+"="+connection.getHeaderField(i));
//        }
        /*
        InputStream content = (InputStream)connection.getInputStream();
        BufferedReader in   = new BufferedReader (new InputStreamReader(content));
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }*/
        int responseCode = connection.getResponseCode();
        if(responseCode == 200){
            String response = "";
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNextLine()){
                response += scanner.nextLine();
                response += "\n";
            }
            scanner.close();

            return response;
        }

        // an error happened
        return null;
    }

    //пробую с клиента отправить джисон
    public void sendPOST(JSONObject req) throws Exception {
        String response = "";
        JSONObject resp = null;
        String data;
        data = URLEncoder.encode(req.toString(), "UTF-8");
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        URL url = new URL ("http://localhost:8080/categories");
        String encoding = Base64.getEncoder().encodeToString(("admin:123").getBytes("UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty  ("Authorization", "Basic " + encoding);

        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");

        OutputStream stream = connection.getOutputStream();
//       OutputStreamWriter wr = new OutputStreamWriter(
//              connection.getOutputStream());
        stream.write(out);
       // stream.flush();
        System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
        connection.disconnect();
        //just info
//        System.out.println(connection.getResponseCode() + connection.getResponseMessage());
//        for(int i=1;i<=8;i++)
//        {
//            System.out.println(connection.getHeaderFieldKey(i)+"="+connection.getHeaderField(i));
//        }


     //   InputStream content = (InputStream)connection.getInputStream();
     //   connection.connect();
//        BufferedReader in   =
//                new BufferedReader (new InputStreamReader(connection.getInputStream()));
//        String line;
//        while ((line = in.readLine()) != null) {
//            response+=line;
//            System.out.println(line);
//        }
//        wr.close();
//        in.close();
      //  resp = new JSONObject(response);
   //    return resp;
        }
    }





/*
//how to implement post and put
public class HttpURLConnectionExample {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static final String GET_URL = "https://localhost:9090/SpringMVCExample";

    private static final String POST_URL = "https://localhost:9090/SpringMVCExample/home";

    private static final String POST_PARAMS = "userName=Pankaj";

    public static void main(String[] args) throws IOException {

        sendGET();
        System.out.println("GET DONE");
        sendPOST();
        System.out.println("POST DONE");
    }

    private static void sendGET() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }

    }

    private static void sendPOST() throws IOException {
        URL obj = new URL(POST_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }
    }

}*/
