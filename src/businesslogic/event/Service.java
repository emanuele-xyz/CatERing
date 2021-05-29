package businesslogic.event;

import businesslogic.kitchen.SummarySheet;
import businesslogic.menu.Menu;

public class Service {

    private final Menu menu;
    private SummarySheet summarySheet;

    public Service(Menu menu) {
        this.menu = menu;
        this.summarySheet = null;
    }

    public boolean hasMenu() {
        return menu != null;
    }

    public SummarySheet getSummarySheet() {
        return summarySheet;
    }

    public SummarySheet generateSummarySheet() {
        return summarySheet = new SummarySheet(menu);
    }
}
