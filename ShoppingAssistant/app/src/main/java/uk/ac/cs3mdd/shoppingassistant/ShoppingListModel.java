package uk.ac.cs3mdd.shoppingassistant;

public class ShoppingListModel {

    String productImage;
    String productName;

    public ShoppingListModel(String productName, String productImage) {
        this.productImage = productImage;
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductName() {
        return productName;
    }
}
