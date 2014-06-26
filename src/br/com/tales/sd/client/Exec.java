package br.com.tales.sd.client;

import br.com.tales.sd.server.main.application.ServiceManager;

import java.util.Scanner;

/**
 * Created by tales on 28/05/14.
 */
public class Exec {
    public static void main(String args[]){

        while (true){
            Scanner sc = new Scanner(System.in);
            String service = sc.nextLine();
            ServiceManager.self().manager(service);
        }

    }
}
