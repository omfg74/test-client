import java.util.Scanner;

public class CheckReg {
    boolean registred;
    public boolean check(){
        System.out.println("Are u registered user y/n?");
        Scanner scanner =new Scanner(System.in);
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("y")){
           registred=true;
        }else {
            registred=false;
        }
        return registred;
    }
}
