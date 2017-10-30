import Objects.User;
import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CreateNewTask  implements Runnable{
private Socket socket = null;
private User user;

    public CreateNewTask(Socket socket) {
        this.socket = socket;
//        this.user = user;
    }

    public void run() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            PackDaJSON packDaJSON = new PackDaJSON();
            JSONObject paxkedJson = packDaJSON.packNewTask(user);
            dataOutputStream.writeUTF(paxkedJson.toString());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String taskResult = dataInputStream.readUTF();
            System.out.println(taskResult);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
