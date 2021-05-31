package businesslogic.kitchen;

import businesslogic.recipe.KitchenProcedure;

public class Activity {

    private String dosesToPrepare;
    private String alreadyPreparedDoses;
    private String preparedDoses;

    // TODO: to be implemented
    public Activity(KitchenProcedure kitchenProcedure) {
    }

    // TODO: to be implemented
    public Activity(KitchenProcedure kitchenProcedure, String toPrepare, String prepared) {

    }

    // TODO: to be implemented
    public boolean hasAlreadyBeenCarriedOut() {
        return false;
    }

    public void edit(String dosesToPrepare, String alreadyPreparedDoses, String preparedDoses) {
        if (dosesToPrepare != null) {
            this.dosesToPrepare = dosesToPrepare;
        }

        if (alreadyPreparedDoses != null) {
            this.alreadyPreparedDoses = alreadyPreparedDoses;
        }

        if (preparedDoses != null) {
            this.preparedDoses = preparedDoses;
        }
    }
}
