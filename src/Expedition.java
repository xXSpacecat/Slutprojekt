import java.util.ArrayList;
import java.util.Random;

public class Expedition {
    public Random rnd = new Random();
    private int daysLeft = rnd.nextInt(1, 3);
    private Character person;
    private boolean isDead = false;
    public int dangerRate = rnd.nextInt(7, 14);
    public int daysOnExp;
    public static int mostItemsFound = 2;
    int expType = rnd.nextInt(2);
    public ArrayList<Item> items = new ArrayList<>();

    public Expedition() {
    }

    public Expedition(Character character, ArrayList<Item> items) {
        person = character;
        createExpedition();
        System.out.println("most items found: " + mostItemsFound);
        this.items = items;
        die();

    }

    public Expedition(Character character, ArrayList<Item> items, Equipment equipment) {
        person = character;
        createExpedition();
        System.out.println("most items found: " + mostItemsFound);
        this.items = items;
        die();
    }

    public static void createExpedition() {
        int expType = new Random().nextInt(2);
        switch (expType) {
            case 0:
                SchoolExp schExp = new SchoolExp();
                placeInMostItems(schExp.goOnSchExp());
                break;
            case 1:
                RHExp rHExp = new RHExp();
                placeInMostItems(rHExp.goOnRHExp());
                break;
            default:
                System.out.println("something went wrong.");
        }
    }

    public ArrayList<Item> foundItems() {
        ArrayList<Item> foundItem = new ArrayList<>();
        for (int i = 0; i <= rnd.nextInt(mostItemsFound - 2, mostItemsFound); i++) {
            System.out.println("they have randomly found " + i);
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

    public static int placeInMostItems(int numberOfItems) {
        mostItemsFound = 2;
        mostItemsFound += numberOfItems;
        System.out.println(mostItemsFound);
        return mostItemsFound;
    }

    public Character getPerson() {
        return person;
    }

    public void setCharacter(Character person) {
        this.person = person;
    }


    public int getMostItemsFound() {
        return mostItemsFound;
    }

    public void setMostItemsFound(int mostItemsFound) {
        this.mostItemsFound = mostItemsFound;
    }
}

