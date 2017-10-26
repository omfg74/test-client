import com.sun.org.apache.regexp.internal.RE;

public class Main {
    public static void main(String[] args) {


        Invite invite = new Invite();
        String hello = invite.hello();
        ConnectionToServer connectToServer = new ConnectionToServer();


                connectToServer.con(hello);
        CheckReg checkReg = new CheckReg();
        boolean reged = checkReg.check();
        if(!reged){
            System.out.println("Wellcome to registration");
            Registration registration = new Registration();
            String[] userData = registration.startRegistration();

        }else if(reged){
            System.out.println("Autorise yourself");
            Autorisation autorisation = new Autorisation(connectToServer);
            boolean auth = autorisation.autorize();
            if (auth){

            }

        }


        System.out.println("DisConnected...");


    }
}
