package br.com.tales.sd.server.main.run;

import br.com.tales.sd.server.main.application.Server;

/**
 * Created by tales on 28/05/14.
 */
public class Exec {
    public static void main(String args[]) throws Exception{
        Server.self().init();
    }
}
