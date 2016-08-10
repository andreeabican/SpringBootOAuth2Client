package Andreea.Bican.Commands;

import Andreea.Bican.ICommand;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andre on 10.08.2016.
 */
public class GoogleRefreshToken implements ICommand {

    @Override
    public void execute() throws URISyntaxException {
        String authorizationCodeURL = sendGet("http://localhost:8181/getGoogleCode", null);

        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        URI uri = new URI(authorizationCodeURL);
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Copy the code from the redirect URI and paste it here");

        BufferedReader br = null;
        String code;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            code = br.readLine();
        } catch (IOException e) {
            code = null;
            e.printStackTrace();
        }

        if(code!= null){
            Map<String,String> header = new HashMap<>();
            header.put("code", code);
            String token = sendGet("http://localhost:8181/getGoogleAccessToken", header);
            System.out.println("Here is your refresh token " + token);
        }else{
            System.out.println("No authorization code");
        }

    }

    public String sendGet(String targetURL, Map<String,String> header) {

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
                output = output + line;
            }

            httpConnection.disconnect();
            return output;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
