package auxiliar;

/**
 * Created by tales on 17/07/14.
 */

import java.util.LinkedList;
 /* Autores: Tales Marinho Godois e Ronaldo Gomes Silva on 12/05/14.
 */
public class HashTableRoute {
    private Node[] T ;
    private int max;

    public HashTableRoute(int size){
        this.T  = new Node[size];
        this.max = T.length;
    }

    public int createKey(String str) {
        String[] test = str.split("");
        int k = 0;
        for(int i = 0;(i<test.length);i++){

            k += test[i].hashCode();
        }
        return k;
    }

    public int hashFunction(int k) {

        int hs = k%this.max;
        return hs;
    }

    public int getSize(){
        return this.max;
    }

}
