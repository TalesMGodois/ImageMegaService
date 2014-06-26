package br.com.tales.sd.server.main.application;

/**
 * Created by tales on 28/05/14.
 */
public interface IServer {


    public void init();
    public void start() throws Exception;

    public  void close();

}
