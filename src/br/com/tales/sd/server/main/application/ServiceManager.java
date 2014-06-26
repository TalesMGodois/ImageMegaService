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

    public void manager(String str){
        String[]  strs = str.split(LocalizedStrings.space());
        if(strs[0].equals(LocalizedStrings.put())){
            //doUpload
//            Upload.self().upload(strs[1]);
        }else if(strs[0].equals(LocalizedStrings.get())){
            //doDownload
        }else{
            System.out.println("Comando não reconhecido");;
        }
    }

}
