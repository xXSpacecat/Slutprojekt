import java.util.ArrayList;
import java.util.Random;

public class Expedition {
    public Random rnd = new Random();
    private int daysLeft = rnd.nextInt(1, 7);
    private Character person;
    private boolean isDead = false;
    public int dangerRate = rnd.nextInt(7, 14);
    public int daysOnExp;
    public int mostItemsFound;
    public ArrayList<Item> items = new ArrayList<>();

    public Expedition() {

    }

    public Expedition(Character character, ArrayList items) {
        person = character;
        this.items = items;
        chooseExpType();
        int expType = rnd.nextInt(4);
        die();

    }

    public Expedition(Character character, ArrayList items, Equipment equipment) {
        person = character;
        this.items = items;
        die();
    }

    private void chooseExpType() {

    }

    public ArrayList<Item> foundItems() {
        ArrayList<Item> foundItem = new ArrayList<>();
        for (int i = 0; i < rnd.nextInt(1, mostItemsFound); i++) {
            foundItem.add(items.get(rnd.nextInt(0, items.size())));
        }
        return foundItem;
    }

    private void die() {
        int deathChance = rnd.nextInt(dangerRate);
        if (deathChance == 1) {
            isDead = true;
        }
    }

    public Character checkExp() {
        daysOnExp++;
        daysLeft--;
        if (daysLeft <= 0 && !isDead) {
            return person;
        }
        return null;
    }

    public Character getPerson() {
        return person;
    }
}

