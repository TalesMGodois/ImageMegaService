import signature.StorageService;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tales on 28/06/14.
 */
public class Storage extends UnicastRemoteObject implements StorageService {
    ConnectionFactory con;
    private static final long serialVersionUID = -8550306338084922644L;

    public Storage(ConnectionFactory connection) throws RemoteException, ClassNotFoundException {
        super();
        this.con = connection;
    }

    @Override
    public Connection initBdConnection(String door, String name, String ip) {
        return null;
//        System.out.println("iniciando conexao com banco de dados dava");
//        String url = "jdbc:postgresql://"+ip +":"+door+"/"+name;
//
//        try {
//            Class.forName("org.postgresql.Driver");
//        } catch (java.lang.ClassNotFoundException e) {
//            System.err.print("ClassNotFoundException: ");
//            System.err.println(e.getMessage());
//        }
//
//        System.out.println("Driver do PostgreSQL selecionado. ");
//
//        try {
//            con = DriverManager.getConnection(url, "postgres", "postgres");
//            return con;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    @Override
    public boolean insertImage(byte[] img) throws RemoteException,AlreadyBoundException,NotBoundException{
        System.out.println(img.toString());

        if(this.con.insert("imagem03",img)){
            return true;
        }else return false;
    }

    @Override
    public byte[] getImage(String name) throws RemoteException,AlreadyBoundException,NotBoundException {
        byte[] img = con.download(name);
        if(img == null){
            System.out.println("Não foi possivel realizar o download de " +name);
            return null;
        }else{
            return img;
        }

    }

    @Override
    public void closeConnection() {

    }
}
