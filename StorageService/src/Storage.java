import signature.StorageService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tales on 28/06/14.
 */
public class Storage extends UnicastRemoteObject implements StorageService {
    Connection db;
    private static final long serialVersionUID = -8550306338084922644L;
    protected Storage() throws RemoteException, ClassNotFoundException {
        super();
        Connection con = new ConnectionFactory().getConnection("root","nimk2000");

    }

    @Override
    public Connection initBdConnection(String door, String name, String ip) {
        System.out.println("iniciando conexao com banco de dados dava");
        String url = "jdbc:postgresql://"+ip +":"+door+"/"+name;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        System.out.println("Driver do PostgreSQL selecionado. ");

        try {
            db = DriverManager.getConnection(url, "postgres", "postgres");
            return db;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insertImage(byte[] img) {
        System.out.println(img.toString());
        return false;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public void closeConnection() {

    }
}
