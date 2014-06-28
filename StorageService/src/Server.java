import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tales on 28/06/14.
 */

    import java.rmi.RMISecurityManager;
    import java.rmi.registry.LocateRegistry;
    import java.rmi.registry.Registry;

    public class Server implements  Runnable{

        int door = 2014;

        private String ip = "localhost";

        private boolean isRun = false;

        private String name = "ImageService";


        private Server(int port){
            this.door = port;
//        changePort(port);
        }

        /* My Singleton */
        private Server(){}


        private static Server INSTANCE = new Server();

        public static Server self(){
            return INSTANCE;
        }
/*My Singelton */

        //Metodo para alterar porta em que o servidor está rodando
//    public  void changePort(int port) {
//        if(port != this.door){
//            this.door = port;
//            System.out.println(LocalizedStrings.self().newPort()+this.door);
//        }else{
//            System.out.println(LocalizedStrings.self());
//        }
//    }


        //Inicia o servidor
        public void init() {
            Thread t = new Thread(Server.INSTANCE);
            t.start();
        }

        public void start() throws Exception {
            System.out.println("Servidor rodando");

            System.setProperty("java.rmi.server.hostname", ip);
            System.setProperty("java.security.policy", "java.policy");

            System.setSecurityManager(new RMISecurityManager());

            Registry r = LocateRegistry.createRegistry(door);

            r.bind(name,new Storage());

            System.out.println("Servidor iniciado...");

            Object lock = new Object();
            synchronized (lock) {
                lock.wait();
            }
        }

        //Mata o Servidor
        public void close() {

        }
        //o Proprio server
        @Override
        public void run()  {
            try{
                start();
            }catch (Exception e){
                System.out.println("Não foi possível iniciar o server");
                e.printStackTrace();
            }
        }
    }