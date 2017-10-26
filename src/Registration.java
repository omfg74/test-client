import java.util.Scanner;

public class Registration {
    private String[] userData =  new String[4];
    public String[] startRegistration(){
        String[] words = new String[4];
        words[0]= "Enter your name...";
        words[1]= "Enter your surname...";
        words[2]= "Enter your login...";
        words[3]= "Enter your password...";

        System.out.println("Enter your Name ");
        for (int i = 0; i <userData.length ; i++) {


        Scanner nameIn = new Scanner(System.in);
        String name = nameIn.nextLine();
        userData[i]=name;
        }
        return userData;
    }

}
