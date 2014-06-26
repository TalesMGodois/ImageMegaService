package br.com.tales.sd.client;

import br.com.tales.sd.server.main.application.ServiceManager;

import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by tales on 28/05/14.
 */
public class Exec {
    public static void main(String args[]) throws RemoteException,NotBoundException{

        while (true){
            Scanner sc = new Scanner(System.in);
            String service = sc.nextLine();
            ServiceManager.self().manager(service);
        }

    }
}
