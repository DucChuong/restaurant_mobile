package dtu.edu.vn.myapplication.database;

import java.io.Serializable;

public class ProductOrder implements Serializable {
    private int productID;
    private String productName;
    private int productQuantity;
    private int productSize;
    private String productDescription;
    private int productDiscount;
    private float productPrice;

    public ProductOrder(int productID, String productName, int productQuantity, int productSize, String productDescription, int productDiscount, float productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productSize = productSize;
        this.productDescription = productDescription;
        this.productDiscount = productDiscount;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductSize() {
        return productSize;
    }

    public void setProductSize(int productSize) {
        this.productSize = productSize;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(int productDiscount) {
        this.productDiscount = productDiscount;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "ProductOrder{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productSize=" + productSize +
                ", productDescription='" + productDescription + '\'' +
                ", productDiscount=" + productDiscount +
                ", productPrice=" + productPrice +
                '}';
    }
}
