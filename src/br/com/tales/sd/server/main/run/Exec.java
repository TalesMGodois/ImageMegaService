package br.com.tales.sd.server.main.run;

import br.com.tales.sd.server.main.application.Server;

/**
 * Created by tales on 28/05/14.
 */
public class Exec {
    /*
     * Index ()
     * Load Balance (Balanceamento de carga)
     *      -> Saber qual maquina é mais aceitavel de realizar operacao
     *      ->
     *
     * Fault Tolerance
     *  -> Detectar falha
     *  -> Servico de ping e tratamento(Difusão)
     * Replicas
     *  ->
     * Protocolo de Inicializacao
     * Elasticidade dinamica
     *
     * 1 - Interessante usar JMS
     *
     */

    public static void main(String args[]) throws Exception{
        Server.self().init();
    }
}
