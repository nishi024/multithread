package it.rajapaksa;

import java.io.*;
import java.net.*;

public class ClientThread extends Thread {
    BufferedReader nome;
    BufferedReader tastiera;// stringa ottenuta da tastiera
    Socket miosocket;
    String stringNomeUtente;// stringa inserita dal client
    String stringRicevutaDalServer;// stringa ricevuta dal server
    DataOutputStream outVersoServer;// stream output
    BufferedReader inDalServer;// stream input

    public ClientThread(Socket miosocket, BufferedReader tastiera, DataOutputStream outVersoServer,
            BufferedReader inDalServer) {
        this.miosocket = miosocket;
        this.tastiera = tastiera;
        this.outVersoServer = outVersoServer;
        this.inDalServer = inDalServer;
    }

    public void run() {
        for (;;) {
            try {// leggo una riga

                System.out.println("4 ... inserisci stringa nome: ");
                stringNomeUtente = tastiera.readLine();// input tastiera

                System.out.println("5 ... attendo risposta dal server ...");
                outVersoServer.writeBytes(stringNomeUtente + '\n');// invio della stringa
                stringRicevutaDalServer = inDalServer.readLine();
                System.out.println("7 ... risposta dal server " + '\n' + stringRicevutaDalServer);// lettura stringa di
                                                                                                  // risposta
                if (stringNomeUtente.equals("FINE") || stringNomeUtente.equals("STOP")) {
                    System.out.println("8 CLIENT: terminazione della conessione");// chiusura connessione
                    miosocket.close();
                    break;
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Errore durante la comunicazione con il server!");
                System.exit(1);
            }
        }

    }
}