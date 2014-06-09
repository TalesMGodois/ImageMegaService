package br.com.tales.sd.server.main.application;

import br.com.tales.sd.server.main.signature.UploadService;

/**
 * Created by tales on 28/05/14.
 */
public class Upload implements UploadService,Runnable {

    public static boolean isRunning = false;

    private Upload(){}

    public static Upload INSTANCE;

    public static Upload self(){
        if(INSTANCE == null){
            INSTANCE = new Upload();
        }
        return  INSTANCE;
    }

    public static void start(){
        if(isRunning != true){
            Thread t = new Thread(Upload.INSTANCE);
            isRunning = true;
            t.start();
        }

    }

    public static void stop(){

    }

    @Override
    public void run() {
        System.out.println("Upload Service Running");
        System.out.println("Digite o enderećo e o nome do arquivo a ser enviado");
        int i = 0;
        while(true){
            System.out.println("Testando...");
        }
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String upload(String obj) {
        if(!isRunning){

        }
        return null;
    }

}
