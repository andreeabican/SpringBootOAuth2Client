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
public class LoginGoogleEndpoint implements ICommand{

    private final String targetURL = "http://localhost:8181/login/google";

    @Override
    public void execute() throws URISyntaxException {

        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        URI uri = new URI(targetURL);
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
