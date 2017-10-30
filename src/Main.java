import Objects.User;
import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
public static  User user = new User();
    public static void main(String[] args) throws IOException {


        Invite invite = new Invite();
        String hello = invite.hello();
        ConnectionToServer connectToServer = new ConnectionToServer();
 connectToServer.con(hello);
        CheckReg checkReg = new CheckReg();
        boolean reged = checkReg.check();
        boolean authorised =false;
        while (!authorised){
        if (reged) {
     Autorisation autorisation = new Autorisation(connectToServer);
     authorised = autorisation.autorize();

        }else if(!reged){
            Registration registration = new Registration(connectToServer);
            String[] collectRegData = registration.startRegistration();
            PackDaJSON packDaJSON = new PackDaJSON();
            JSONObject packedREg = packDaJSON.packRegistrationData(collectRegData);
            boolean registred = registration.sendRegistrationData(packedREg,connectToServer);
            if(registred){
                Autorisation autorisation = new Autorisation(connectToServer);
                autorisation.autorize();
            }else {
                System.out.println("failed to authorise try again later");
                connectToServer.socket.close();
                System.exit(1);
            }
        }
        }
        int command = -1;
        while (command==-1||command==2||command==1){
            System.out.println("1 Create new task");
            System.out.println("2 List tasks");
            System.out.println("3 exit");

                    TaskPicker taskPicker = new TaskPicker();
            command =taskPicker.pickTheTask();
            if(command==1){
                CreateNewTask createNewTask = new CreateNewTask(connectToServer.socket,user);
                createNewTask.run();
            }else if(command == 2){

            }else {
                System.exit(0);
            }
        }

    }
}