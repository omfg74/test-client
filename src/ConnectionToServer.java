import org.json.simple.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

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
              outputStream.write(logPas.toString().getBytes());
              outputStream.flush();
              DataInputStream dataInputStream = new DataInputStream(in);
              boolean answer = dataInputStream.readBoolean();
             if(!answer){
                 System.out.println("Auth Failed");
             }else if(answer){
                 System.out.println("Permission Granted");
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

    public void sendN() {

        try {
            outputStream.write("n\n".getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRegistrationData(JSONObject packedRegistratioonData) {
        try {
            outputStream.write(packedRegistratioonData.toString().getBytes());
        } catch (IOException e) {
            System.out.println("Error sending registration data");
            e.printStackTrace();
        }

    }
}





