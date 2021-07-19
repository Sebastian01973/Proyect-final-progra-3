package models;

import java.util.ArrayList;

public class Trolley {

    public ArrayList<Product> products;

    public Trolley() {
        products = new ArrayList<>();
    }

    public int size(){
        return products.size();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public Product getProduct(int index) {
        return products.get(index);
    }

    public boolean isExistProduct(String name){
        for (Product product : products) {
            if (name.equals(product.getNameProduct())) {
                return true;
            }
        }
        return false;
    }

    public Product getProduct(String nameProduct) {
        for (Product product : products) {
            if (nameProduct.equals(product.getNameProduct())) {
                return product;
            }
        }
        return null;
    }

    public double calculateTotalProduct(String nameProduct){
        double total = 0;
        for (Product product : products) {
            if (nameProduct.equals(product.getNameProduct())) {
                total = (product.getUnits()* product.getPrice());
            }
        }
        return total;
    }

    public void deleteProduct(Product product){
        products.removeIf(p -> p.getNameProduct().equals(product.getNameProduct()));
    }

    public double calculateTotal(){
        double totalBuy = 0;
        for (Product product :products) {
            totalBuy += (product.getUnits()* product.getPrice());
        }
        return totalBuy;
    }

    public boolean isEmpty(){
        return products.isEmpty();
    }

    public void showProducts(){
        for (Product product : products) {
            System.out.println(product.toString());
        }
    }
}
