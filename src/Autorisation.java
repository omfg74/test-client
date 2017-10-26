import org.json.simple.JSONObject;

import java.util.Scanner;

public class Autorisation {
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
        boolean auth = connectionToServer.sendAuthData(logPas);

        return auth;// здесь впилить проверку паролья
}
}
