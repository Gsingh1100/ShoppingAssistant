package uk.ac.cs3mdd.shoppingassistant;

public class DayMeal {

    String meal_id;
    String mealCategory;
    String name;
    String mealImage;
    String date;

    public DayMeal(String meal_id, String mealCategory, String name, String mealImage, String date) {
        this.meal_id = meal_id;
        this.mealCategory = mealCategory;
        this.name = name;
        this.mealImage = mealImage;
        this.date = date;
    }

    public String getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(String meal_id) {
        this.meal_id = meal_id;
    }

    public String getMealCategory() {
        return mealCategory;
    }

    public void setMealCategory(String mealCategory) {
        this.mealCategory = mealCategory;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
