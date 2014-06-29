import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tales on 29/06/14.
 */
public class ConnectionFactory {
    public ConnectionFactory()  {


    }

    public Connection getConnection(String user,String password){
        try{
            System.out.println("REALIZANDO CONEXAO");
            try{
                Class.forName("com.mysql.jdbc.Driver");

            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }
            return DriverManager.getConnection("jdbc:mysql://localhost/imageService",user,password);
        }catch (SQLException e){
            System.out.println("CONEXAO NAO REALIZADA");
            throw new RuntimeException(e);
        }
    }

    public void dbCreate(String dtb){

    }

}
