import Objects.User;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConnectionToServer {
  int port =3535;
//  String ip;
    User user;
InputStream in;
    OutputStream outputStream;
    Socket socket = null;
  public void con (String ip) {

      try {
          InetAddress appr = InetAddress.getByName(ip);
          socket = new Socket(appr, port);


      } catch (Exception x) {
          x.printStackTrace();
          System.out.println("Connection error, try again later");
      }


  }}