import auxiliar.Put;
import signature.ClusterService;

import java.util.concurrent.Callable;

/**
 * Created by tales on 19/07/14.
 */
public class ThreadSave implements Callable<Boolean> {

    private static int works = 0;

    private int id;

    private byte[] img;

    private String name;

    public int getId() {
        return id;
    }

    public ThreadSave(byte[] img, String name, int id){

        this.img = img;
        this.name = name;
        this.id = id;
    }

    @Override
    public Boolean call() throws Exception {
        Storage.sincronizeNode();
        if(Storage.getCon().insert(name, img)){
            Storage.clearThread(this);
            return true;
        }else{
            Storage.clearThread(this);
            return false;
        }
    }
}
