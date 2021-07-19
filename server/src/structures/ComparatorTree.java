package structures;

import models.Product;

import java.util.Comparator;

public class ComparatorTree implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getNameProduct().compareTo(o2.getNameProduct());

    }
}
