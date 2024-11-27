package dk.mobile.bwurger5;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
@Entity
public class Burger {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String ingredients;


    public Burger() {}

    public Burger(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }


    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
}