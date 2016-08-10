package Andreea.Bican;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

@SpringBootApplication
public class OAuthClientApplication {

    private static String message = "Enter endpoint ";

    public static void main(String[] args) throws IOException {

        System.out.println("Whenever you wanna exit enter q");

        BufferedReader br = null;
        CommandParser commandParser = new CommandParser();
        ICommand command = null;

        try {

            br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {

                System.out.print(message);
                String input = br.readLine();

                if ("q".equals(input)) {
                    System.out.println("Exit!");
                    System.exit(0);
                }

                command = commandParser.getCommand(input);
                command.execute();
                System.out.println("-----------\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
}
