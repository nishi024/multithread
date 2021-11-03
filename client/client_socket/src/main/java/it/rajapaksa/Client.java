package it.rajapaksa;

public class Client {

    public static void main(String[] args) {
        ClientStr cliente = new ClientStr();// istanza client
        cliente.connetti();// connessione al server
        cliente.comunica();// comunicazione con il server
    }

}
