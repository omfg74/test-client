import org.json.simple.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionToServer {
  int port =3535;
//  String ip;
DataInputStream in;
    DataOutputStream out;
    Socket socket = null;
  public void con (String ip) {

      try{
          InetAddress appr = InetAddress.getByName(ip);
          socket = new Socket(appr,port);
          InputStream sin = socket.getInputStream();
          OutputStream sout = socket.getOutputStream();
         in = new DataInputStream(sin);
          out = new DataOutputStream(sout);




      } catch (Exception x) {
          x.printStackTrace();
          System.out.println("Connection error, try again later");
      }



      }
      public boolean sendAuthData(JSONObject logPas){
          try {
              out.write(logPas.toString().getBytes());
              out.flush();
              boolean answer = in.readBoolean();
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
            out.writeUTF("y");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendN() {
        try {
            out.writeUTF("n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





