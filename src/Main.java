import Objects.User;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {


        Invite invite = new Invite();
        String hello = invite.hello();
        ConnectionToServer connectToServer = new ConnectionToServer();


                connectToServer.con(hello);
        CheckReg checkReg = new CheckReg();
        boolean reged = checkReg.check();
        if(!reged){

            Boolean answer = connectToServer.sendN();
            if(!answer) {

                registration(connectToServer);
                boolean answerIfExsits = connectToServer.answerIfExsits();
                if(answerIfExsits){
                    System.out.println("Choose another login or Authorise y/n");
                    registration(connectToServer);
                }else {
                    System.out.println("User created authorise now... ");
                    Autorisation autorisation = new Autorisation(connectToServer);
                    boolean auth = autorisation.autorize();
                    if (auth){

                    }
                }
                //ели такой уже есть
            }else {
                System.out.println("Authorise yourself");
                Autorisation autorisation = new Autorisation(connectToServer);
                boolean auth = autorisation.autorize();
                if (auth){

                }
            }

        }else if(reged){
            connectToServer.sendY();
            System.out.println("Authorise yourself");
            Autorisation autorisation = new Autorisation(connectToServer);
            boolean auth = autorisation.autorize();
            if (auth){

            }

        }


        System.out.println("DisConnected...");


    }
     static void registration(ConnectionToServer connectToServer){
         System.out.println("Wellcome to registration");
         Registration registration = new Registration();
         String[] userData = registration.startRegistration();
         PackDaJSON packDaJSON = new PackDaJSON();
         JSONObject packedRegistratioonData = packDaJSON.packRegistrationData(userData);
         connectToServer.sendRegistrationData(packedRegistratioonData);
         boolean answerIfExsits = connectToServer.answerIfExsits();
         if(answerIfExsits){
             System.out.println("Choose another login or Authorise y/n");
             Scanner sc = new Scanner(System.in);
             String a = sc.nextLine();
             if(a.equalsIgnoreCase("y")){
                 connectToServer.sendN();
                 registration(connectToServer);
             }else if(a.equalsIgnoreCase("n")){
                 authorisation(connectToServer);
             }

         }else {
             boolean autorised = false;
             while (!autorised){
                 authorisation(connectToServer);

             }
         }
    }
    public static void authorisation(ConnectionToServer connectToServer){
        System.out.println("User created authorise now... ");
        Autorisation autorisation = new Autorisation(connectToServer);
        boolean auth = autorisation.autorize();
        if (auth){
            User nameSurname  = connectToServer.receiveNameAndSurname();

            System.out.println("Hello "+nameSurname.getName()+" "+nameSurname.getSurName()+"!");
            System.out.println("Main menu:");

        }else {
            connectToServer.sendN();
            registration(connectToServer);
        }
    }
}
