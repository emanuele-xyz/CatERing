package businesslogic;

import businesslogic.event.EventManager;
import businesslogic.kitchen.KitchenTaskManager;
import persistence.KitchenTaskPersistence;
import businesslogic.menu.MenuManager;
import businesslogic.recipe.RecipeManager;
import businesslogic.shift.ShiftManager;
import businesslogic.user.UserManager;
import persistence.MenuPersistence;
import persistence.ShiftPersistence;

public class CatERing {
    private static CatERing singleInstance;

    public static CatERing getInstance() {
        if (singleInstance == null) {
            singleInstance = new CatERing();
        }
        return singleInstance;
    }

    private final MenuManager menuMgr;
    private final MenuPersistence menuPersistence;

    private final KitchenTaskManager kitchenTaskMgr;
    private final KitchenTaskPersistence kitchenTaskPersistence;

    private final ShiftManager shiftMgr;
    private final ShiftPersistence shiftPersistence;

    private final RecipeManager recipeMgr;
    private final UserManager userMgr;
    private final EventManager eventMgr;

    private CatERing() {
        menuMgr = new MenuManager();
        menuPersistence = new MenuPersistence();
        menuMgr.addEventReceiver(menuPersistence);

        kitchenTaskMgr = new KitchenTaskManager();
        kitchenTaskPersistence = new KitchenTaskPersistence();
        kitchenTaskMgr.addEventReceiver(kitchenTaskPersistence);

        shiftMgr = new ShiftManager();
        shiftPersistence = new ShiftPersistence();
        shiftMgr.addEventReceiver(shiftPersistence);

        recipeMgr = new RecipeManager();
        userMgr = new UserManager();
        eventMgr = new EventManager();
    }

    public MenuManager getMenuManager() {
        return menuMgr;
    }

    public KitchenTaskManager getKitchenTaskManager() {
        return kitchenTaskMgr;
    }

    public RecipeManager getRecipeManager() {
        return recipeMgr;
    }

    public UserManager getUserManager() {
        return userMgr;
    }

    public EventManager getEventManager() {
        return eventMgr;
    }

    public ShiftManager getShiftManager() {
        return shiftMgr;
    }
}
