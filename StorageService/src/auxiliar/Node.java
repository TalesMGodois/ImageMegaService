package auxiliar;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by tales on 14/07/14.
 */
public class Node implements Serializable{
    private int id;
    private int door;
    private String ip;
    private String name;
    private boolean activated;

    private static final long serialVersionUID = 2806421523585360625L;

    //Se é uma replica ou se é uma particao
    private String type;

    public Node(){}

    public void upNode(int id,int door,String ip,String name, String type){
        this.id = id;
        this.door = door;
        this.ip = ip;
        this.name = name;
        this.type = type;
        this.activated = true;
    }

    public void changeDoor(int door){
        this.door= door;
    }


    public void deleteNode(){
        this.activated = false;
    }




}
