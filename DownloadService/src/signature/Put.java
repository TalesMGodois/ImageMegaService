package signature;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tales on 07/07/14.
 */
public class Put {

    private String name;
    private String addr;

    private byte[] image;
    private List<Byte[]> images;

    public Put(String name, String addr){
        setName(name);
        setAddr(addr);
    }

    private void setAddr(String addr){
        this.addr = addr;
    }
    private void setName(String name){
        this.name = name;
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
