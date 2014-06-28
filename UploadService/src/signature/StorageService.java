package signature;

import java.sql.Connection;

/**
 * Created by tales on 28/06/14.
 */
public interface StorageService {

    public Connection initBdConnection(int door,String name,String ip);

    public boolean insertImage();

    public String getImage();

    public void closeConnection();

}
