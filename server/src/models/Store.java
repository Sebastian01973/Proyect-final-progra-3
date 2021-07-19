package models;

import structures.BinaryAVLTree;
import structures.ComparatorTree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Store {

    private BinaryAVLTree<Product> productsStore;
    private ArrayList<Client> clients;
    private double shopSilver;

    public Store() {
        shopSilver = 0;
        clients = new ArrayList<>();
        productsStore = new BinaryAVLTree<>(new ComparatorTree());
    }


    public void deleteProduct(Product product){
        productsStore.delete(product);
    }


    public void setPriceProduct(Product product,double price){
        Product product1 = productsStore.searchElement(product);
        product1.setPrice(price);
    }

    public void addUnitProduct(String nameProduct,int units){
        Product product1 = searchProduct(nameProduct);
        product1.setUnits(product1.getUnits()+units);
    }

    public synchronized void setUnitProduct(Product product,int units){
        Product product1 = productsStore.searchElement(product);
        if (units <= product1.getUnits()){
            product1.setUnits(product.getUnits()-units);
        }
    }

    public boolean isExistProduct(String name){
        return productsStore.isExist(new Product(name,null,0,0));
    }

    public Product searchProduct(String name){
        return productsStore.searchElement(new Product(name,null,0,0));
    }

    public Client getClient(String nick){
        for (Client client:clients) {
            if (nick.equals(client.getNickName())){
                return client;
            }
        }
        return null;
    }

    public void addProduct(Product product){
        productsStore.insert(product);
    }

    public Product createProduct(String name, TypeProduct tp,int unit, double price){
        return new Product(name,tp,unit,price);
    }

    public Client createClient(String nick,String password,String name){
        return new Client(nick,password,name);
    }

    public boolean existClient(String name){
        for (Client cl: clients) {
            if (cl.getNickName().equals(name)){
             return true;
            }
        }
        return false;
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public TypeProduct getType(int value){
        switch (value){
            case 1: return TypeProduct.TECHNOLOGY;
            case 2: return TypeProduct.GAMES;
            case 3: return TypeProduct.CLOTHES;
            case 4: return TypeProduct.FOOD;
            default: return null;
        }
    }

    public TypeProduct getType(String value){
        switch (value){
            case "TECHNOLOGY": return TypeProduct.TECHNOLOGY;
            case "GAMES": return TypeProduct.GAMES;
            case "CLOTHES": return TypeProduct.CLOTHES;
            case "FOOD": return TypeProduct.FOOD;
            default: return null;
        }
    }

    public Iterator<Product> getIterator(){
        return productsStore.travelOf(null,1);
    }

    public void showClients(){
        for (Client client:clients) {
            System.out.println(client.toString());
        }
    }

    public void showProducts(){
        if (productsStore.isEmpty()){
            System.out.println("No hay productos");
        }else {
            Iterator<Product> iterator = getIterator();
            while (iterator.hasNext()){
                Product product = iterator.next();
                System.out.println(product.toString());
            }
        }
    }

    public ArrayList<Object[]> getMatrixClients(){
        ArrayList<Object[]> arrayClients = new ArrayList<>();
        for (Client client:clients) {
            arrayClients.add(client.toVectorObject());
        }
        arrayClients.sort(new Comparator<Object[]>() {
            @Override
            public int compare(Object[] o1, Object[] o2) {
                String[] last = String.valueOf(o1[2]).split(",");
                String[] last2 = String.valueOf(o2[2]).split(",");
                return last[1].compareTo(last2[1]);
            }
        });
        return arrayClients;
    }

    public synchronized ArrayList<Object[]> getMatrixProducts(TypeProduct typeProduct){
        ArrayList<Object[]> auxDatasArrayList = new ArrayList<>();
        Iterator<Product> iterator = getIterator();
        if (!productsStore.isEmpty()){
            while (iterator.hasNext()){
                Product product = iterator.next();
                if (typeProduct.equals(product.getTypeProduct())){
                    auxDatasArrayList.add(product.toObjectVector());
                }
            }
        }
        return auxDatasArrayList;
    }

    public synchronized ArrayList<Object[]> getMatrixProducts() {
        ArrayList<Object[]> auxDatasArrayList = new ArrayList<>();
        Iterator<Product> iterator = getIterator();
        if (!productsStore.isEmpty()){
            while (iterator.hasNext()){
                Product product = iterator.next();
                auxDatasArrayList.add(product.toObjectVector());
            }
        }
        return auxDatasArrayList;
    }
}
