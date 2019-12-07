package menus;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class MainMenu extends AbstractMenu {

    public MainMenu(Prompt prompt) {
        this.prompt = prompt;
        this.id = 1;
        init();
    }

    private void init() {
        String[] arrayOfOptions = new String[6];
        arrayOfOptions[0] = "Gather Food";
        arrayOfOptions[1] = "Gather Wood";
        arrayOfOptions[2] = "Build Tools";
        arrayOfOptions[3] = "Build Boat";
        arrayOfOptions[4] = "Rest";
        arrayOfOptions[5] = "Escape";

        menuInputScanner = new MenuInputScanner(arrayOfOptions);
    }

    @Override
    public int present() {
        return super.present();
    }

}
