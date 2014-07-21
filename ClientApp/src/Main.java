import javax.xml.ws.Service;
import java.io.File;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Created by tales on 28/06/14.
 */
public class Main {
    public static void main(String args[]) throws RemoteException,NotBoundException,NoSuchElementException,AlreadyBoundException {
        System.out.println("App Cliente em execucao");
        String service ="run";

        Scanner sc = new Scanner(System.in);
        service = sc.nextLine();
        ServiceManager.self().manager(service);


    }
}
