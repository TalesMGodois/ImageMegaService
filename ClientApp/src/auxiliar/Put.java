package auxiliar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tales on 07/07/14.
 */
public class Put implements Serializable{

    private static final long serialVersionUID = 2806421523585360625L;

    private String name;
    private String addr;
    private byte[] image;
    private List<Byte[]> images;

    public Put(String name, String addr){
        this.name = name;
        this.addr = addr;
    }

    public String getName(){
        return this.name;
    }

    public String getAddr(){
        return this.addr;
    }

    public void setImage(String addrImage){
        try{
            BufferedImage image = ImageIO.read(new File(addrImage));
            ByteArrayOutputStream imageByte = new ByteArrayOutputStream();
            ImageIO.write(image,"PNG",imageByte);
            this.image = imageByte.toByteArray();

        }catch (IOException e){
            System.out.println("Imagem não encontrada");
        }
    }

    public byte[] getImage(){
        return  this.image;
    }

}
