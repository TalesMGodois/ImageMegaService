package br.com.tales.sd.connection;

import java.rmi.Remote;

/**
 * Created by tales on 28/05/14.
 */
public interface ConnectionListenerService extends Remote,Runnable{
    public void listener();
}
