/**
 * Created by tales on 28/06/14.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String userDb = "root";
        String password = "nimk2000";
        try{
            int door = Integer.parseInt(args[0]);
            Server.self().start("root","nimk2000",door);
        }catch (Exception e){
            Server.self().start("root","nimk2000");
            e.printStackTrace();
        }
//        Server.self().start("root","nimk2000");


    }
}
