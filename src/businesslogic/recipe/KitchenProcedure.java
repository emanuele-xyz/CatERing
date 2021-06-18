package businesslogic.recipe;

import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class KitchenProcedure {

    public abstract int getId();

    public Stream<KitchenProcedure> getRequiredKitchenProceduresStream() {
        List<KitchenProcedure> rkps = new ArrayList<>();
        rkps.add(this);
        rkps.addAll(loadRequiredKitchenProcedures(this));
        return rkps.stream().distinct();
    }

    public List<KitchenProcedure> getRequiredKitchenProcedures() {
        return getRequiredKitchenProceduresStream().collect(Collectors.toList());
    }

    // STATIC METHODS FOR PERSISTENCE

    protected static Map<Integer, Recipe> recipesCache = new HashMap<>();
    protected static Map<Integer, Preparation> preparationsCache = new HashMap<>();

    public static KitchenProcedure loadKitchenProcedureByID(int id) {
        String query = String.format("SELECT type FROM catering.kitchen_procedures WHERE id = %d", id);
        PersistenceManager.executeQuery(query, rs -> {
            String type = rs.getString("type");
            switch (type.charAt(0)) {
                case 'r' -> Recipe.loadRecipeById(id);
                case 'p' -> Preparation.loadPreparationByID(id);
            }
        });

        if (recipesCache.containsKey(id)) return recipesCache.get(id);
        else return preparationsCache.getOrDefault(id, null);
    }

    public static List<KitchenProcedure> loadRequiredKitchenProcedures(KitchenProcedure kitchenProcedure) {
        if (kitchenProcedure instanceof Recipe recipe) {
            return Recipe.loadRequiredKitchenProcedures(recipe);
        } else if (kitchenProcedure instanceof Preparation preparation) {
            return Preparation.loadRequiredKitchenProcedures(preparation);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
