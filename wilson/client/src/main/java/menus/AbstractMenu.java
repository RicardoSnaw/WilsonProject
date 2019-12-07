package menus;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class AbstractMenu implements Menu {

    protected Prompt prompt;
    protected MenuInputScanner menuInputScanner;
    protected int id;


    /**
     * @return (int) option chosen by the user.
     * Usage: In order to give this method proper implementation, one must:
     * <p>
     * - Define messages in the menuInputScanner
     * - Define options to be presented,
     * <p>
     * Finally: Call the super.
     */
    @Override
    public int present() {

        return prompt.getUserInput(menuInputScanner);
    }

    @Override
    public int getId() {
        return id;
    }
}
