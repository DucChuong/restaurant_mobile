package dtu.edu.vn.myapplication.database;

public class ProductSize {
    private int id;
    private int product_id;
    private int size_id;
    private float price;

    public ProductSize(int id, int product_id, int size_id, float price) {
        this.id = id;
        this.product_id = product_id;
        this.size_id = size_id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
