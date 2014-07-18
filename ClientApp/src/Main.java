import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by tales on 28/06/14.
 */
public class Main {
    public static void main(String args[]) throws RemoteException,NotBoundException,NoSuchElementException,AlreadyBoundException {
        System.out.println("App Cliente em execucao");
        String service ="run";
        while(service != "exit"){
            Scanner sc = new Scanner(System.in);
            try{
                service = sc.nextLine();
                ServiceManager.self().manager(service);

            }catch (NoSuchElementException e){
                System.out.println("Fechando Programa...");
                service = "exit";
            }
        }
    }
}
