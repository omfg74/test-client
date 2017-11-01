import Objects.User;
import org.json.simple.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CreateNewTask  extends Thread{
private Socket socket = null;
private User user;
private int command;

    public CreateNewTask(Socket socket, User user, int command) {
        this.socket = socket;
        this.user = user;
        this.command = command;
    }

    public void run() {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            PackDaJSON packDaJSON = new PackDaJSON();
            JSONObject paxkedJson = packDaJSON.packNewTask(user,command);
            dataOutputStream.writeUTF(paxkedJson.toString());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String taskResult = dataInputStream.readUTF();
            System.out.println(taskResult);
            JsonParser jsonParser = new JsonParser();
            JSONObject jo = jsonParser.parseTheTaskResult(taskResult);
            if(jo.get("taskType").toString().equals("RENDER")){

                System.out.println(jo.get("status").toString()+" id "+jo.get("id").toString());
               // получить окончательный статус
                String taskType =completeParcer();




            }else if(jo.get("taskType").toString().equals("list")){
                jsonParser.parseList(taskResult);
            }
            System.out.println(taskResult);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String completeParcer(){
        DataInputStream dataInputStream = null;
        String s = "";
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
           s=  dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JsonParser jsonParser1 = new JsonParser();
        JSONObject jsonObject = jsonParser1.parseTheTaskResult(s);
        String taskType = (String) jsonObject.get("taskType");
        if(taskType.equalsIgnoreCase("update")){

           long id = Long.parseLong(  jsonObject.get("id").toString());
           String name = (String) jsonObject.get("name");
           String status = (String) jsonObject.get("status");
           String result = (String) jsonObject.get("result");
            System.out.println("Task "+ id+" is "+status);

        }
        return taskType;
    }
}
