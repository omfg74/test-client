import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        Invite invite = new Invite();
        String hello = invite.hello();
        ConnectionToServer connectToServer = new ConnectionToServer();


                connectToServer.con(hello);


        System.out.println("Connected...");


    }
}
