import java.util.ArrayList;
import java.util.Random;

public class Expedition {
    public Random rnd = new Random();
    private int daysLeft = rnd.nextInt(1, 3);
    private Character person;
    private boolean isDead = false;
    public int dangerRate = rnd.nextInt(7, 14);
    public int daysOnExp;
    public static int mostItemsFound = 1;
    int expType = rnd.nextInt(2);
    public ArrayList<Item> items = new ArrayList<>();

    private static String story;

    public Expedition() {
    }

    public Expedition(Character character, ArrayList<Item> items) {
        person = character;
        createExpedition(person);
        this.items = items;
        die();

    }

    public Expedition(Character character, ArrayList<Item> items, Equipment equipment) {
        person = character;
        createExpedition(person);
        this.items = items;
        die();
    }

    public static void createExpedition(Character character) {
        int expType = new Random().nextInt(2);
        switch (expType) {
            case 0:
                SchoolExp schExp = new SchoolExp(character);
                placeInMostItems(schExp.goOnSchExp());
                story = schExp.agrStory(character);
                break;
            case 1:
                RHExp rHExp = new RHExp(character);
                placeInMostItems(rHExp.goOnRHExp());
                story = rHExp.agrStory(character);
                break;
            default:
                System.out.println("something went wrong.");
        }
    }

    public ArrayList<Item> foundItems() {
        ArrayList<Item> foundItem = new ArrayList<>();
        for (int i = 0; i <= rnd.nextInt(mostItemsFound - 1, mostItemsFound); i++) {
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
        return mostItemsFound;
    }

    public static void tellStory(String story) {
        System.out.println(story);
    }

    public Character getPerson() {
        return person;
    }

    public String getStory() {
        return story;
    }


}
