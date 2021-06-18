package businesslogic.recipe;

import persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class Preparation extends KitchenProcedure {

    private int id;
    private String name;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    // STATIC METHODS FOR PERSISTENCE

    public static Preparation loadPreparationByID(int preparationID) {
        String query = String.format("SELECT * FROM catering.kitchen_procedures WHERE id = %d", preparationID);
        PersistenceManager.executeQuery(query, rs -> {
            final int id = rs.getInt("id");
            if (id <= 0) return;

            Preparation preparation = preparationsCache.get(id);
            if (preparation == null) {
                preparation = new Preparation();
                preparation.id = id;
                preparation.name = rs.getString("name");

                preparationsCache.put(preparation.id, preparation);
            }
        });

        return preparationsCache.get(preparationID);
    }

    public static List<KitchenProcedure> loadRequiredKitchenProcedures(Preparation preparation) {
        List<KitchenProcedure> requiredKitchenProcedures = new ArrayList<>();

        String query = String.format("SELECT required_preparation_id FROM catering.kitchen_procedures_requirements WHERE kitchen_procedure_id = %d", preparation.id);
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
