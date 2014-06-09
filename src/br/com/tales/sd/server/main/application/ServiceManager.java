package br.com.tales.sd.server.main.application;

/**
 * Created by tales on 08/06/14.
 */
public class ServiceManager {

    private ServiceManager() {
    }

    private static ServiceManager INSTANCE = new ServiceManager();

    public static ServiceManager self() {
        if (INSTANCE == null) {
            INSTANCE = new ServiceManager();
        }
        return INSTANCE;
    }

    public void Manager(String str){
        String[]  strs = str.split(LocalizedStrings.space());
        if(strs[0] == "put"){
            //doUpload
        }else if(strs[0] == "get"){
            //doDownload
        }else{
            System.out.println("Comando não reconhecido");;
        }


    }

}
