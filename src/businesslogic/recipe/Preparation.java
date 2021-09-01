package businesslogic.recipe;

import persistence.PersistenceManager;

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
}
