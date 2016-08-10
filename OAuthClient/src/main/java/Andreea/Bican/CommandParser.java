package Andreea.Bican;

import Andreea.Bican.Commands.AutoLoginEndpoint;
import Andreea.Bican.Commands.LoginFacebookEndpoint;
import Andreea.Bican.Commands.LoginGoogleEndpoint;

/**
 * Created by andre on 10.08.2016.
 */
public class CommandParser {

    public ICommand getCommand(String input){
        if(input.toLowerCase().startsWith("/logingoogle")){
            return new AutoLoginEndpoint();
        }else if(input.toLowerCase().startsWith("/login/google")){
            return new LoginGoogleEndpoint();
        }else if(input.toLowerCase().startsWith("/login/facebook")) {
            return new LoginFacebookEndpoint();
        }else{
            return null;
        }
    }
}
