import java.io.File;

/**
 * Created by tales on 18/07/14.
 */
public class SendImage implements Runnable {

    private File file;
    private String name;
    private static int id =0;

    public SendImage(File fl){
        this.file = fl;
        id++;
    }

    @Override
    public void run() {
        try{
            System.out.println("Iniciando Thread");
            String path = this.file.getName();
            String path2 = this.file.getAbsolutePath();
            ServiceManager.self().sendFile(this.file);
//            ServiceManager.self().manager(this.service +" "+ this.file.getName() + " " +this.file.getAbsolutePath());
            id --;
        }catch (Exception e){
            e.printStackTrace();
            id--;
            System.out.println("Problema na requisićão");
        }
    }
}
