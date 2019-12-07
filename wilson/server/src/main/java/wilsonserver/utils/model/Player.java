package wilsonserver.utils.model;

public class Player {

    private int health;
    private boolean escaped;
    private String name;
    private int actions;

    public Player(String name) {
        this.name = name;
        health = Constants.BASE_PLAYER_HEALTH;
    }

    public boolean isDead(){
        return health <= 0;
    }


    public int getActions() {
        return actions;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }

    public void useAction() {
        this.actions--;
    }

    public void escape() {
        this.escaped = true;
    }

    public String getName() {
        return name;
    }

    public void setEscaped() {
        this.escaped = true;
    }
}
