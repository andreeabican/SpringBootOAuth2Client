package Andreea.Bican.Commands;

import Andreea.Bican.ICommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andre on 11.08.2016.
 */
public class ClassEndpoint implements ICommand{

    private String targetURL = "http://localhost:";

    private String targetEndpoint = "/class";

    @Override
    public void execute(int port) throws Exception {
        System.out.println("Enter class id");

       String id = readInput();

        Map<String, String> header = new HashMap<>();
        if(id != null){
            header.put("id", id);
        }

        sendGet(targetURL + port + targetEndpoint, header);
    }

    private String readInput(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendGet(String targetURL, Map<String,String> header) {

        try {
            URL restServiceURL = new URL(targetURL);

            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
            if (header != null) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    httpConnection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }

            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                    (httpConnection.getInputStream())));

            String output = "", line;
            System.out.println("Output from Server:  \n");

            while ((line = responseBuffer.readLine()) != null) {
                output = output + line + "\n";
            }
            httpConnection.disconnect();
            System.out.println(output);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
