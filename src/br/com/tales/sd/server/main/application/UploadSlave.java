package br.com.tales.sd.server.main.application;

import br.com.tales.sd.server.main.signature.UploadService;

import java.io.File;

/**
 * Created by tales on 29/05/14.
 */
public class UploadSlave implements Runnable{


    public UploadSlave(String obj){
        File object = new File(obj);

    }

    @Override
    public void run() {
        System.out.println("teste");
    }
}
