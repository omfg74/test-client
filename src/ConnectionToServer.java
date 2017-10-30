import Objects.User;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionToServer {
  int port =3535;
//  String ip;
InputStream in;
    OutputStream outputStream;
    Socket socket = null;
  public void con (String ip) {

      try{
          InetAddress appr = InetAddress.getByName(ip);
          socket = new Socket(appr,port);
          in = socket.getInputStream();
          outputStream = socket.getOutputStream();





      } catch (Exception x) {
          x.printStackTrace();
          System.out.println("Connection error, try again later");
      }



      }
      public boolean sendAuthData(JSONObject logPas){
          try {
              PrintWriter pw = new PrintWriter(outputStream);
              pw.println(logPas.toString());
              pw.flush();
              DataInputStream dataInputStream = new DataInputStream(in);
              boolean answer = dataInputStream.readBoolean();
             if(!answer){
                 System.out.println("Auth Failed");
                 socket.close();
             }else if(answer){
                 System.out.println("Permission Granted");
                 User nameSurname = receiveNameAndSurname();
                 System.out.println("Hello "+nameSurname.getName() + " "+nameSurname.getSurName());

                 return true;

             }


          } catch (IOException e) {
              e.printStackTrace();
          }

          return false;
      }

    public void sendY() {

        try {
            String y = "y\n";
            outputStream.write(y.getBytes());
            outputStream.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean sendN() {
        boolean answer = false;
        try {
            outputStream.write("n\n".getBytes());
            outputStream.flush();
//            DataInputStream inputStream = new DataInputStream(in);
//            answer = inputStream.readBoolean();
//            if (answer){
//                answer =true;
//            }
//            else if(!answer){
//                answer = false;
//            };
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public void sendRegistrationData(JSONObject packedRegistratioonData) {
        try {
          PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.println(packedRegistratioonData.toString());
//            dataInputStream.writeUTF("\n");
            pw.flush();
        } catch (IOException e) {
            System.out.println("Error sending registration data");
            e.printStackTrace();
        }

    }

    public boolean answerIfExsits() {
         Boolean answer  =false;
          DataInputStream dataInputStream = new DataInputStream(in);
        try {
            answer = dataInputStream.readBoolean();
            System.out.println(answer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }

    public User receiveNameAndSurname() {
        User user = new User();
          DataInputStream dataInputStream = new DataInputStream(in);
        try {
//            boolean authOk = dataInputStream.readBoolean();
         boolean authOk= true;//костыль ебаный
            if (authOk){
                String nameSurname = dataInputStream.readUTF();//здесь встали
            JsonParser jsonParser = new JsonParser();
            user = jsonParser.parseNameSurname(nameSurname, user);
        }else {
                receiveNameAndSurname();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
    public  void autorised(){
        DataInputStream inputStream = new DataInputStream(in);
        System.out.println("1 Create new task");
        System.out.println("2 List all my tasks");
        System.out.println("3 exit");
        Scanner scanner = new Scanner(System.in);
        int ans = Integer.parseInt(scanner.nextLine());
        if(ans==3){
            System.exit(0);

        }else if(ans==2){


        try {

            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.write(ans);
            outputStream.flush();
            CreateNewTask createNewTask = new CreateNewTask(socket);
           createNewTask.run();
           autorised();
    }catch (Exception e){
            System.out.println("Only digits!");
            autorised();
        }
        }else {
            //Получаем статистику
        }
      }
}





