import auxiliar.Node;

/**
 * Created by tales on 16/07/14.
 */
public class Console implements Runnable {
    @Override
    public void run() {
        consoleView();

    }

    public void consoleView (){

        if(Cluster.getPartitions().size() == 0 && Cluster.getRepliques().size() == 0){
            System.out.println("Sistema não está pronto para uso, Não existem particoes ou replicas");
        }else{
            if(Cluster.getPartitions().size() != 0){
                System.out.println("Particões ATIVAS" );
                for(Node s: Cluster.getPartitions()){
                    System.out.println(""+ s.getName()+ ":("+ s.getIp()+ ":"+ s.getDoor()+ ")");
                }
            }if(Cluster.getRepliques().size() !=0){
                System.out.println("Replicas ATIVAS:");
                for(Node s: Cluster.getRepliques()){
                    System.out.println( s.getName()+ "("+ s.getIp()+ ":"+ s.getDoor()+ ")");
                }
            }
        }
        try{
            Thread.sleep(1000);

        }catch(Exception e){
            e.printStackTrace();
        }
        consoleView();
    }
}
