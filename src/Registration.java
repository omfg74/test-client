import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Registration {
    private String[] userData =  new String[4];

    public Registration(ConnectionToServer connectToServer) {
    }

    public String[] startRegistration(){
        String[] words = new String[4];
        words[0]= "Enter your name...";
        words[1]= "Enter your surname...";
        words[2]= "Enter your login...";
        words[3]= "Enter your password...";

//        System.out.println("Enter your Name ");
        for (int i = 0; i <userData.length ; i++) {
            System.out.println(words[i]);

        Scanner nameIn = new Scanner(System.in);
        String name = nameIn.nextLine();
        userData[i]=name;

        }
        return userData;
    }
    public boolean sendRegistrationData(JSONObject packedRegistratioonData,ConnectionToServer connectionToServer) {
        boolean answer = false;
        try {


            PrintWriter pw1 = new PrintWriter(connectionToServer.socket.getOutputStream());
            pw1.println("n\n");
            pw1.flush();
            DataOutputStream dataOutputStream = new DataOutputStream(connectionToServer.socket.getOutputStream());
            dataOutputStream.writeUTF(packedRegistratioonData.toString());
//            dataInputStream.writeUTF("\n");
            dataOutputStream.flush();
            DataInputStream dataInputStream = new DataInputStream(connectionToServer.socket.getInputStream());
            answer= dataInputStream.readBoolean();
            System.out.println("Registration "+answer);
        } catch (IOException e) {
            System.out.println("Error sending registration data");
            e.printStackTrace();
        }
return  answer;
    }

}
