package uk.ac.cs3mdd.shoppingassistant;

public class DayPlan {

    String mealID;
    String date;
    String mealType;

    public DayPlan(String mealID, String date, String mealType) {
        this.mealID = mealID;
        this.date = date;
        this.mealType = mealType;
    }

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

}
