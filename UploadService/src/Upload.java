import signature.StorageService;
import signature.UploadService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

/**
 * Created by tales on 28/06/14.
 */
public class Upload extends UnicastRemoteObject implements UploadService {


    private static final long serialVersionUID = -8550306338084922644L;

    private String ip = "localhost";
    private byte[] image;
    private int door;
    public Upload() throws RemoteException, AlreadyBoundException{
        super();
    }

    @Override
    public String getName() throws RemoteException, AlreadyBoundException {
        return null;
    }

    @Override
    public void make(String imageAddr) throws RemoteException, AlreadyBoundException, NotBoundException{
        HashMap<String,String> servicesNames = new HashMap<String, String>();
        servicesNames.put("storage", "StorageService");

        setImage(imageAddr);
        System.out.println("Imprimindo valores");
        String serviceName = "storageService";
        System.setProperty("java.security.policy", "java.policy");
        System.setSecurityManager(new RMISecurityManager());
        Registry r = LocateRegistry.getRegistry(this.ip, 2015);
        StorageService send = (StorageService) r.lookup(servicesNames.get("storage"));
        if(getImage() != null){
            System.out.println("realizando upload...");
            boolean b = send.insertImage(getImage());
            System.out.println(b);
        }
//        send.initBdConnection("3000","imageStorage","localhost");
    }

    @Override
    public byte[] getImage() throws RemoteException, AlreadyBoundException, NotBoundException {
        return this.image;
    }

    @Override
    public void setImage(String addrImage) throws RemoteException, AlreadyBoundException, NotBoundException {
        try{
            BufferedImage image = ImageIO.read(new File(addrImage));
            ByteArrayOutputStream imageByte = new ByteArrayOutputStream();
            ImageIO.write(image,"PNG",imageByte);
            this.image = imageByte.toByteArray();

        }catch (IOException e){
            System.out.println("Imagem não encontrada");
        }
    }
}
