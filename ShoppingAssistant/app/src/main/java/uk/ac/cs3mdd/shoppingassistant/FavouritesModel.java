package uk.ac.cs3mdd.shoppingassistant;

public class FavouritesModel {

    String id;
    String name;
    String mealImage;
    String favourite;

    public FavouritesModel(String id, String name, String mealImage, String favourite) {
        this.id = id;
        this.name = name;
        this.mealImage = mealImage;
        this.favourite = favourite;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getFavourite() {
        return favourite;
    }

    public void setFavourite(String favourite) {
        this.favourite = favourite;
    }
}
