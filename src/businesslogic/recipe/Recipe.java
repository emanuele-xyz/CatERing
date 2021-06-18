package businesslogic.recipe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.PersistenceManager;
import persistence.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Recipe extends KitchenProcedure {

    private int id;
    private String name;

    private Recipe() {}

    public Recipe(String name) {
        id = 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getId() {
        return id;
    }

    public String toString() {
        return name;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static ObservableList<Recipe> loadAllRecipes() {
        String query = "SELECT * FROM catering.kitchen_procedures WHERE type = 'r'";
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                int id = rs.getInt("id");
                if (recipesCache.containsKey(id)) {
                    Recipe rec = recipesCache.get(id);
                    rec.name = rs.getString("name");
                } else {
                    Recipe rec = new Recipe(rs.getString("name"));
                    rec.id = id;
                    recipesCache.put(rec.id, rec);
                }
            }
        });
        ObservableList<Recipe> ret =  FXCollections.observableArrayList(recipesCache.values());
        Collections.sort(ret, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe o1, Recipe o2) {
                return (o1.getName().compareTo(o2.getName()));
            }
        });
        return ret;
    }

    public static ObservableList<Recipe> getAllRecipes() {
        return FXCollections.observableArrayList(recipesCache.values());
    }

    public static Recipe loadRecipeById(int id) {
        if (recipesCache.containsKey(id)) return recipesCache.get(id);
        Recipe rec = new Recipe();
        String query = "SELECT * FROM catering.kitchen_procedures WHERE id = " + id;
        PersistenceManager.executeQuery(query, new ResultHandler() {
            @Override
            public void handle(ResultSet rs) throws SQLException {
                    rec.name = rs.getString("name");
                    rec.id = id;
                    recipesCache.put(rec.id, rec);
            }
        });
        return rec;
    }

    public static List<KitchenProcedure> loadRequiredKitchenProcedures(Recipe recipe) {
        List<KitchenProcedure> requiredKitchenProcedures = new ArrayList<>();

        String query = String.format("SELECT required_preparation_id FROM catering.kitchen_procedures_requirements WHERE kitchen_procedure_id = %d", recipe.id);
        PersistenceManager.executeQuery(query, rs -> {
            final int requiredPreparationID = rs.getInt(1);
            if (requiredPreparationID <= 0) return;

            Preparation preparation = preparationsCache.get(requiredPreparationID);
            if (preparation == null) {
                preparation = Preparation.loadPreparationByID(requiredPreparationID);
            }
            requiredKitchenProcedures.add(preparation);
        });

        return requiredKitchenProcedures;
    }
}
