package uk.ac.cs3mdd.shoppingassistant;

public class Meal {

    private String id;
    private String name;
    private String category;
    private String image;

    public Meal(String id, String name, String category, String image){

        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
