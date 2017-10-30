import Objects.User;
import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Autorisation {
    User user  =new User();

   private ConnectionToServer connectionToServer;
    public Autorisation(ConnectionToServer connectToServer) {
        this.connectionToServer = connectToServer;

    }

    public boolean autorize(){
  System.out.println("Hello enter your login");
        Scanner loginSc = new Scanner(System.in);
        String login  = loginSc.nextLine();
        System.out.println("Enter Password ");
        loginSc = new Scanner(System.in);
        String password = loginSc.nextLine();
        PackDaJSON packDaJSON = new PackDaJSON();
        JSONObject logPas = packDaJSON.putData(login,password);
        boolean auth = sendAuthData(logPas,login);

        return auth;// здесь впилить проверку паролья
}
    public boolean sendAuthData(JSONObject logPas,String login){
        boolean answer = false;
        try {

//            user.setLogin(login);
            DataOutputStream dataOutputStream = new DataOutputStream(connectionToServer.socket.getOutputStream());
            dataOutputStream.writeUTF(logPas.toString());
            dataOutputStream.flush();
            DataInputStream dataInputStream = new DataInputStream(connectionToServer.socket.getInputStream());
            //здес ждем подтверждение авторизации;
           answer = dataInputStream.readBoolean();
            System.out.println("Auth "+answer);
            if(!answer){
                System.out.println("Auth Failed");
                connectionToServer.socket.close();
                System.exit(1);
            }else if(answer){
//                System.out.println("Permission Granted");
                //получить имя фамилию
//                 User nameSurname = receiveNameAndSurname();
//                 System.out.println("Hello "+nameSurname.getName() + " "+nameSurname.getSurName());

                answer = true;

            }
} catch (IOException e) {
            e.printStackTrace();
        }
    return answer;
    }}
