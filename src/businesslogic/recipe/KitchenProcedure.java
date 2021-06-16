package businesslogic.recipe;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface KitchenProcedure {

    Stream<KitchenProcedure> getRequiredKitchenProceduresStream();

    default List<KitchenProcedure> getRequiredKitchenProcedures() {
        return getRequiredKitchenProceduresStream().collect(Collectors.toList());
    }

    int getId();
}
