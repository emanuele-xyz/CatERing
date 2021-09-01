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
        List<KitchenProcedure> requiredKitchenProcedures = new ArrayList<>();

        String query = String.format("SELECT required_preparation_id FROM catering.kitchen_procedures_requirements WHERE kitchen_procedure_id = %d", kitchenProcedure.getId());
        PersistenceManager.executeQuery(query, rs -> {
            final int requiredPreparationID = rs.getInt(1);
            if (requiredPreparationID <= 0) return;

            // Load and add preparation
            Preparation tmp = preparationsCache.get(requiredPreparationID);
            if (tmp == null) {
                tmp = Preparation.loadPreparationByID(requiredPreparationID);
            }
            requiredKitchenProcedures.add(tmp);

            // Load and add all preparations required by the previously loaded preparation
            requiredKitchenProcedures.addAll(Preparation.loadRequiredKitchenProcedures(tmp));
        });

        return requiredKitchenProcedures;
    }
}
