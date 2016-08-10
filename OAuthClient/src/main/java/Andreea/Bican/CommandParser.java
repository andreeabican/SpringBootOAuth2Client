package Andreea.Bican;

import Andreea.Bican.Commands.LoginGoogleEndpoint;

/**
 * Created by andre on 10.08.2016.
 */
public class CommandParser {

    public ICommand getCommand(String input){
        if(input.startsWith("/loginGoogle")){
            return new LoginGoogleEndpoint();
        }else{
            return null;
        }
    }
}
