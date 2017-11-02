import java.util.Scanner;

public class Invite {
    public String hello(){
        System.out.println("Hello enter address...(for localhost press Enter");
        Scanner sc = new Scanner(System.in);

        String addr = sc.nextLine();
        return addr;
    }
}
