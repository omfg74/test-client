import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ConnectionToServer {
  int port =3535;
//  String ip;


  public void con (String ip) {
      Socket socket = null;
      try{
          InetAddress appr = InetAddress.getByName(ip);
          socket = new Socket(appr,port);
          InputStream sin = socket.getInputStream();
          OutputStream sout = socket.getOutputStream();
          DataInputStream in = new DataInputStream(sin);
          DataOutputStream out = new DataOutputStream(sout);
          BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
          String line = null;
          System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks.");
          System.out.println();

          while (true) {
              line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
              System.out.println("Sending this line to the server...");
              out.writeUTF(line); // отсылаем введенную строку текста серверу.
              out.flush(); // заставляем поток закончить передачу данных.
              line = in.readUTF(); // ждем пока сервер отошлет строку текста.
              System.out.println("The server was very polite. It sent me this : " + line);
              System.out.println("Looks like the server is pleased with us. Go ahead and enter more lines.");
              System.out.println();
          }
      } catch (Exception x) {
          x.printStackTrace();
      }



      }
      }





