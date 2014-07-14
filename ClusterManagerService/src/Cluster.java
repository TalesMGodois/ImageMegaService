import auxiliar.Node;
import signature.ClusterService;

import java.util.List;

/**
 * Created by tales on 14/07/14.
 */
public class Cluster implements ClusterService{

    private List<Node> repliques;
    private List<Node> partitions;

    @Override
    public boolean testNode(Node node) {
        return false;
    }

    @Override
    public boolean createPartiion(String name, String type, String ip, int door) {
        return false;
    }

    @Override
    public void addStorage(int index,Node node) {
        repliques.add(node);
    }

    @Override
    public void sendup(byte[] img) {

    }
}
