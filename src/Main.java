import com.sun.org.apache.regexp.internal.RE;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        Invite invite = new Invite();
        String hello = invite.hello();
        ConnectionToServer connectToServer = new ConnectionToServer();


                connectToServer.con(hello);
        CheckReg checkReg = new CheckReg();
        boolean reged = checkReg.check();
        if(!reged){
            connectToServer.sendN();
            System.out.println("Wellcome to registration");
            Registration registration = new Registration();
            String[] userData = registration.startRegistration();
            PackDaJSON packDaJSON = new PackDaJSON();
            JSONObject packedRegistratioonData = packDaJSON.packRegistrationData(userData);
            connectToServer.sendRegistrationData(packedRegistratioonData);


        }else if(reged){
            connectToServer.sendY();
            System.out.println("Autorise yourself");
            Autorisation autorisation = new Autorisation(connectToServer);
            boolean auth = autorisation.autorize();
            if (auth){

            }

        }


        System.out.println("DisConnected...");


    }
}
