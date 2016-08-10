package Andreea.Bican.Commands;

import Andreea.Bican.ICommand;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by andre on 10.08.2016.
 */
public class LocalHostEndpoint implements ICommand {
    private final String uriString = "http://localhost:8181";

    @Override
    public void execute() throws URISyntaxException {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        URI uri = new URI(uriString);
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
