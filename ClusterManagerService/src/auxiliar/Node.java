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

    public void upNode(HashMap<String,String> map){
        this.id = Integer.parseInt(map.get("id"));
        this.door = Integer.parseInt(map.get("door"));
        this.ip = map.get("ip");
        this.name = map.get("name");
        this.type = map.get("type");
        this.activated = true;
    }

    public void changeDoor(int door){
        this.door= door;
    }


    public void deleteNode(){
        this.activated = false;
    }




}
