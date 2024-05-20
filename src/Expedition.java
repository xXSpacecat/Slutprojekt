import java.util.ArrayList;
import java.util.Random;

public class Expedition {// with a character items can be found in this class
    public Random rnd = new Random();
    private int daysLeft = rnd.nextInt(4, 9);
    private Character person;
    private boolean isDead = false;
    public int dangerRate = rnd.nextInt(7, 14);
    public int daysOnExp;
    public static int mostItemsFound = 1;
    public ArrayList<Item> items = new ArrayList<>();

    private static String story;

    public Expedition() {//constructor
    }

    public Expedition(Character character, ArrayList<Item> items) {//constructor
        person = character;
        createExpedition(person);
        this.items = items;
        die();

    }

    public Expedition(Character character, ArrayList<Item> items, Equipment equipment) {//constructor with equipment
        person = character;
        createExpedition(person);
        this.items = items;
        die();
    }

    public static void createExpedition(Character character) {//there are currently two types of expeditions which that will be used will be chosen randomly
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

    public ArrayList<Item> foundItems() {//Items found during expedition will be stored here, amount will be decided from other expedition methods
        ArrayList<Item> foundItem = new ArrayList<>();
        for (int i = 0; i <= rnd.nextInt(mostItemsFound - 1, mostItemsFound); i++) {
            foundItem.add(items.get(rnd.nextInt(0, items.size())));
        }
        return foundItem;
    }

    private void die() {//Chance of death during expedition is different depending on expedition type
        int deathChance = rnd.nextInt(dangerRate);
        if (deathChance == 1) {
            isDead = true;
        }
    }

    public Character checkExp() {//after a number of days the expedition will end if the person is alive
        daysOnExp++;
        daysLeft--;
        if (daysLeft <= 0 && !isDead) {
            return person;
        }
        return null;
    }

    public static int placeInMostItems(int numberOfItems) {//The range of which items can be found will grow during expedition and here the summary will be added to the property
        mostItemsFound = 2;
        mostItemsFound += numberOfItems;
        return mostItemsFound;
    }


    public Character getPerson() {//getter
        return person;
    }

    public String getStory() {//getter
        return story;
    }


}
