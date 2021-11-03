package it.rajapaksa;

import java.io.*;
import java.net.*;

public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket miosocket;
    BufferedReader tastiera;
    String stringNomeUtente;
    String stringaRicevutaDalServer;// stringa ricevuta dal server
    DataOutputStream outVersoServer;// stream output
    BufferedReader inDalServer;// stream input

    public Socket connetti() {
        System.out.println("2 CLIENT partito in esecuzione ...");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));// input tastiera
            miosocket = new Socket(nomeServer, portaServer);// creazione socket con indirizzo e porta

            outVersoServer = new DataOutputStream(miosocket.getOutputStream());// istanza per output verso al server
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));// istanza per input dal
                                                                                                // server

        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);

        }
        return miosocket;
    }

    public void comunica() {// comunicazione con il server
        Listener l = new Listener(miosocket);
        l.start();
        ClientThread c = new ClientThread(miosocket, tastiera, outVersoServer, inDalServer);
        c.start();
    }
}
