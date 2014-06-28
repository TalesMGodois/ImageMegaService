

/**
 * Created by tales on 28/05/14.
 */
public class LocalizedStrings {

    private LocalizedStrings() {
    }

    private static LocalizedStrings INSTANCE;

    public static LocalizedStrings self() {
        if (INSTANCE == null) {
            INSTANCE = new LocalizedStrings();
        }
        return INSTANCE;
    }


    public static String space(){return  " ";}

    public static String newPort(){
        return "Nova porta:";
    }

    public static String running(){
        return "Server running...";
    }
    public static String alreadyInUse(){
        return "Já estamos usando essa porta.";
    }

    public static String whattYouDo(){
        return  "deseja fazer um upload ou um download?";
    };

    public static  String needANumber(){
        return "Código inválido, preciso que digite [1] ou [2],";
    }
    public static  String put(){
        return "put";
    }
    public static  String get(){
        return "get";
    }
    public static  String serverNotRunning(){
        return "Server not running";
    }


}
