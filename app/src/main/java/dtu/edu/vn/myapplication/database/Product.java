package dtu.edu.vn.myapplication.database;

public class Product {
    private int id;
    private String name;
    private String description;
    private boolean status;
    private String imgSrc;
    private String categories;

    private int discount;

    public Product(String name, String description, String imgSrc, String categories, boolean status, int discount, int id) {
        this.name = name;
        this.description = description;
        this.imgSrc = imgSrc;
        this.categories = categories;
        this.status = status;
        this.discount = discount;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
