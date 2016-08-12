package Andreea.Bican.Commands;

import Andreea.Bican.ICommand;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by andre on 10.08.2016.
 */
public class LoginFacebookEndpoint implements ICommand {

    private final String targetURL = "http://localhost:";

    private final String targetEndpoint = "/login/facebook";

    @Override
    public void execute(int port) throws URISyntaxException {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        URI uri = new URI(targetURL + port + targetEndpoint);
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
