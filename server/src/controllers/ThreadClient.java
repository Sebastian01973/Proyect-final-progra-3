package controllers;

import models.Client;
import models.Product;
import models.Store;
import models.TypeProduct;
import nets.Connection;
import org.json.simple.DeserializationException;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;
import persistence.SecurityFileJson;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class ThreadClient extends Thread {

    private Store store;
    private IObserved iObserved;
    private Connection connection;
    private SecurityFileJson securityFileJson;
    private Socket socket;

    public ThreadClient(Socket socket, Store store, SecurityFileJson securityFileJson, IObserved iObserved) {
        this.socket = socket;
        connection = new Connection(socket);
        this.securityFileJson = securityFileJson;
        this.store = store;
        this.iObserved = iObserved;
    }

    @Override
    public void run() {
        execute();
    }

    private void execute() {
        while (!socket.isClosed()) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (connection.availableWeb() > 0) {
                int option = connection.readInt();
//                System.out.println("Opcion:" + option);
                switch (option) {
                    case 1:
                        this.registerClient();
                        break; //Register Client
                    case 2:
                        this.verifyClient();
                        break; //delete Client
                    case 3:
                        this.deleteProductKart();
                        break;
                    case 4:
                        this.addProductCart();
                        break;
                    case 5:
                        this.sendDatesClient();
                        break;
                    case 6:
                        this.buyClient();
                        break;
                    case 7:
                        this.addCashClient();
                        break;
                    case 8:
                        this.getProductClient();
                        break;
                    case 9:
                        this.cleanKart();
                        break;
                    case 10:
                        this.sendCustomerList();
                        break; //Send clients
                    case 11:
                        this.addProduct();
                        break; // add Product
                    case 12:
                        this.sendProducts();
                        break; //Send Products
                    case 13:
                        this.getProduct();
                        break; //getProduct
                    case 14:
                        this.removeClient();
                        break;
                    case 15:
                        this.deleteProduct();
                        break;
                    case 16:
                        this.modifyProduct();
                        break;
                    case 17:
                        this.searchProducts();
                        break;
                    case 18:
                        this.deleteProduct2();
                        break;
                    case 19:
                        this.sendProductsStore();
                        break;
                    case 20:
                        this.sendNamesImgaes();
                        break;
                    default:
                        break;
                }
            }
        }
        Logger.getGlobal().info("Cerro Conexion");
    }

    private void sendNamesImgaes() {
//        System.out.println("Enviar imagenes");
        File file = new File("images/");
        String[] images = file.list();
        String pathImages = securityFileJson.writeImages(images);
        connection.writeString(pathImages);
    }

    private void cleanKart() {
        String nickClient = connection.readString();
        Client client = store.getClient(nickClient);
        int size = client.getTrolley().size();
        for (int i = 0; i < size; i++) {
            Product product = client.getTrolley().getProduct(i);
            store.addUnitProduct(product.getNameProduct(), product.getUnits());
        }
        client.getTrolley().getProducts().clear();
    }

    private void addCashClient() {
        System.out.println("Agregar dinero");
        String nickClient = connection.readString();
        double value = connection.readDouble();
        Client client = store.getClient(nickClient);
        client.setWallet(client.getWallet()+value);
    }

    private void buyClient() {
        System.out.println("Finalizar compra");
        String nickClient = connection.readString();
        Client client = store.getClient(nickClient);
        if (!client.getTrolley().isEmpty()){
            connection.writeString(ConstantServer.WITH_PRODUCT_CLIENT);
            if (client.isEnoughMoney()){
                connection.writeString(ConstantServer.WITH_MONEY);
                client.setWallet(client.getWallet()-client.getTrolley().calculateTotal());
                client.getTrolley().getProducts().clear();
            }else {
                connection.writeString(ConstantServer.WITHOUT_MONEY);
            }
        }else {
            connection.writeString(ConstantServer.WITHOUT_PRODUCT_CLIENT);

        }
    }

    private void sendDatesClient() {
//        System.out.println("Enviar datos del cliente");
        String nick = connection.readString();
        Client client = store.getClient(nick);
        connection.writeDouble(client.getWallet());
    }

    private void deleteProductKart() {
        System.out.println("Eliminar producto del carrito");
        String nameClient = connection.readString();
        String nameProduct = connection.readString();
        Client client = store.getClient(nameClient);
        if (client.getTrolley().isExistProduct(nameProduct)){
            Product productClient = client.getTrolley().getProduct(nameProduct);
            client.getTrolley().deleteProduct(productClient);
            store.addUnitProduct(nameProduct, productClient.getUnits());
        }
        double total = client.getTrolley().calculateTotal();
        connection.writeDouble(total);
        store.showClients();
    }

    private void addProductCart() {
        System.out.println("Agregar al carrito");
        String nick = connection.readString();
        String jsonProduct = connection.readString();
        Object[] product = securityFileJson.readProductClient(jsonProduct);
        Client client = store.getClient(nick);
        String nameProduct = String.valueOf(product[0]);
        int units = Integer.parseInt(String.valueOf(product[2]));
        Product product1 = store.searchProduct(nameProduct);
        store.setUnitProduct(product1, units);
        if (!client.getTrolley().isExistProduct(nameProduct)) {
            Product productClient = store.createProduct(nameProduct, store.getType(String.valueOf(product[1])),
                    units, Double.parseDouble(String.valueOf(product[3])));
            client.getTrolley().addProduct(productClient);
        } else {
            Product productClient = client.getTrolley().getProduct(nameProduct);
            productClient.setUnits(productClient.getUnits() + units);
        }
        double totalProduct = client.getTrolley().calculateTotalProduct(nameProduct);
        connection.writeDouble(totalProduct);
        double totalBuy = client.getTrolley().calculateTotal();
        connection.writeDouble(totalBuy);
    }

    private void sendProductsStore() {
//        System.out.println("Productos Totales");
        String json = securityFileJson.writeProductToFile(store.getMatrixProducts());
        connection.writeString(json);
    }


    private void searchProducts() {
        System.out.println("Busca los productos");
        int option = connection.readInt();
        TypeProduct typeProduct = store.getType(option);
        String json = securityFileJson.writeProductToFile(store.getMatrixProducts(typeProduct));
        connection.writeString(json);
    }

    private void modifyProduct() {
        System.out.println("Modificar el producto");
        String name = connection.readString();
        String modify = connection.readString();
        Object[] obProduct = securityFileJson.readProduct(modify);
        int type = Integer.parseInt(String.valueOf(obProduct[1]));
        int unit = Integer.parseInt(String.valueOf(obProduct[2]));
        double price = Double.parseDouble(String.valueOf(obProduct[3]));
        Product product = store.searchProduct(name);
        product.setUnits(unit);
        product.setPrice(price);
        product.setTypeProduct(store.getType(type));
    }

    private void deleteProduct() {
        System.out.println("Eliminar producto");
        String name = connection.readString();
        if (store.isExistProduct(name)) {
            Product product = store.searchProduct(name);
            File image = new File("images/"+product.getCodeImage());
            if (!product.getCodeImage().equals("empty.png")){
                image.delete();
            }
            store.deleteProduct(product);
        }
    }

    private void deleteProduct2() {
        System.out.println("Eliminar producto 2");
        String name = connection.readString();
        if (store.isExistProduct(name)) {
            connection.writeString(ConstantServer.EXIST_PRODUCT);
            Product product = store.searchProduct(name);
            store.deleteProduct(product);
        } else {
            connection.writeString(ConstantServer.DOES_NOT_PRODUCT);
        }
    }

    public boolean isClosedSocket() {
        return socket.isClosed();
    }

    private void removeClient() {
        writeProducts();
        connection.closeSocket();
        iObserved.deleteClient();
    }


    private void getProduct() {
        System.out.println("Obtenemos el producto");
        String name = connection.readString();
        Product product = store.searchProduct(name);
        Object[] obProduct = product.toObjectVector();
        String jsonProduct = securityFileJson.writeProduct(obProduct);
        connection.writeString(jsonProduct);
        connection.sendFile(new File("images/" + product.getCodeImage()));
    }

    private void getProductClient() {
        System.out.println("Obtenemos el producto para cliente");
        String name = connection.readString();
        Product product = store.searchProduct(name);
        if (product != null){
            connection.writeString(ConstantServer.DOES_DELETE_PRODUCT);
            Object[] obProduct = product.toObjectVector();
            String jsonProduct = securityFileJson.writeProductClients(obProduct);
            connection.writeString(jsonProduct);
        }else {
            connection.writeString(ConstantServer.DELETE_PRODUCT);
        }
    }

    private void addProduct() {
        System.out.println("Agregar producto");
        String nameProduct = connection.readString();
        if (store.isExistProduct(nameProduct)) {
            connection.writeString(ConstantServer.EXIST_PRODUCT);
        } else {
            connection.writeString(ConstantServer.DOES_NOT_PRODUCT);
            String product = connection.readString();
            Object[] obProduct = securityFileJson.readProduct(product);
            String name = String.valueOf(obProduct[0]);
            int type = Integer.parseInt(String.valueOf(obProduct[1]));
            int unit = Integer.parseInt(String.valueOf(obProduct[2]));
            double price = Double.parseDouble(String.valueOf(obProduct[3]));
            Product p1 = store.createProduct(name, store.getType(type), unit, price);
            String verify = connection.readString();
            if (verify.equals(ConstantServer.WITH_IMAGE)) {
                String nameImage = connection.writeFile();
                p1.setCodeImage(nameImage);
            } else if (verify.equals(ConstantServer.WITHOUT_IMAGE)) {
                p1.setCodeImage("empty.png");
            }
            store.addProduct(p1);
        }
    }

    private String writeProducts() {
        securityFileJson.writeProductToFile(store.getMatrixProducts(), SecurityFileJson.PATH_FILE_PRODUCT);
        JsonObject root = null;
        try {
            root = (JsonObject) Jsoner.deserialize(new FileReader(SecurityFileJson.PATH_FILE_PRODUCT));
        } catch (DeserializationException | IOException e) {
            e.printStackTrace();
        }
        return root.toJson();
    }

    private void sendProducts() {
        connection.writeString(writeProducts());
    }

    private String writeClients() {
        securityFileJson.writeClientToFile(store.getMatrixClients());
        JsonObject root = null;
        try {
            root = (JsonObject) Jsoner.deserialize(new FileReader(SecurityFileJson.PATH_FILE));
        } catch (DeserializationException | IOException e) {
            e.printStackTrace();
        }
        return root.toJson();
    }

    private void sendCustomerList() {
        connection.writeString(writeClients());
    }

    private void registerClient() {
        System.out.println("Registro de cliente");
        String nick = connection.readString();
        if (store.existClient(nick)) {
            connection.writeString(ConstantServer.EXIST_CLIENT);
        } else {
            connection.writeString(ConstantServer.DOES_NOT_CLIENT);
            String newClient = connection.readString();
            String[] datesClient = newClient.split("-");
            Client client = store.createClient(datesClient[0], datesClient[1], datesClient[2]);
            store.addClient(client);
            store.showClients();
            writeClientToFile();
        }
    }

    public void writeClientToFile() {
        securityFileJson.writeClientToFile(store.getMatrixClients());
    }

    private void verifyClient() {
        System.out.println("Login");
        String dates = connection.readString();
        String[] datesClient = dates.split("-");
        if (store.existClient(datesClient[0])) {
            Client client = store.getClient(datesClient[0]);
            if (client.getPassword().equals(datesClient[1])) {
                connection.writeString(ConstantServer.ACCEPT_CLIENT);
            } else {
                connection.writeString(ConstantServer.ERROR_LOGIN);
            }
        } else {
            connection.writeString(ConstantServer.DOES_NOT_CLIENT);
        }
    }
}
