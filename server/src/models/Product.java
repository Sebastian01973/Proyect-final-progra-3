package models;

public class Product {

    private String nameProduct;
    private int units;
    private double price;
    private TypeProduct typeProduct;
    private String codeImage;

    public Product(String nameProduct,TypeProduct typeProduct, int units, double price) {
        this.nameProduct = nameProduct;
        this.typeProduct = typeProduct;
        this.units = units;
        this.price = price;
        codeImage = "";
    }

    public String getCodeImage() {
        return codeImage;
    }

    public void setCodeImage(String codeImage) {
        this.codeImage = codeImage;
    }

    public TypeProduct getTypeProduct() {
        return typeProduct;
    }

    public void setTypeProduct(TypeProduct typeProduct) {
        this.typeProduct = typeProduct;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)){
            return false;
        }
        Product product = (Product) obj;
        return product.nameProduct.equals(this.nameProduct);
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double totalPriceProducts(){
        return (units*price);
    }

    public Object[] toObjectVector() {
        return new Object[]{
                this.nameProduct,this.typeProduct.toString(),this.units,this.price,
                totalPriceProducts(),this.codeImage
        };
    }

    @Override
    public String toString() {
        return "Product{" +
                "nameProduct='" + nameProduct + '\'' +
                ", units=" + units +
                ", price=" + price +
                ", typeProduct=" + typeProduct +
                ", codeImage=" + codeImage +
                '}';
    }
}
