package controllers;

import models.Client;
import models.Product;
import models.Store;
import persistence.SecurityFileJson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements IObserved{

    private static final int PORT = 22320;
    private ServerSocket serverSocket;
    private Store store;
    private ArrayList<ThreadClient> clientArrayList;
    private SecurityFileJson securityFileJson;

    private boolean active;

    public Server() {
        System.out.println("Start!");
        active = true;
        try {
            serverSocket = new ServerSocket(PORT);
            store = new Store();
            clientArrayList = new ArrayList<>();
            securityFileJson = new SecurityFileJson();
            this.readClientToFile(); //Read clients
            this.readProductsToFile(); //Read Products
            while (active){
                Socket socket = serverSocket.accept();
                ThreadClient threadClient = new ThreadClient(socket,store,securityFileJson,this);
                threadClient.start();
                clientArrayList.add(threadClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void readProductsToFile() {
        ArrayList<Object[]> readClients = securityFileJson.readProductToFile();
        for (Object[] object : readClients){
            Product product = store.createProduct(String.valueOf(object[0]),
                    store.getType(String.valueOf(object[1])),
                    Integer.parseInt(String.valueOf(object[3])),
                    Double.parseDouble(String.valueOf(object[2])));
            product.setCodeImage(String.valueOf(object[5]));
            store.addProduct(product);
        }
    }

    public void readClientToFile(){
        ArrayList<Object[]> readClients = securityFileJson.readClientToFile();
        for (Object[] object : readClients){
            Client client = store.createClient(String.valueOf(object[0]),
                    String.valueOf(object[1]),String.valueOf(object[2]));
            client.setWallet(Double.parseDouble(String.valueOf(object[3])));
            store.addClient(client);
        }
    }

    @Override
    public void deleteClient() {
        clientArrayList.removeIf(ThreadClient::isClosedSocket);
    }
}
