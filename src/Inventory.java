import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Item> allItems = new ArrayList<Item>();
    public Random rnd = new Random();
    private int waterCounter;
    private int foodCounter;


    public Inventory() {
        createItems();
        createInventory();
        counter();
    }

    private void counter() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals("water")) {
                waterCounter++;
            } else if (items.get(i).name.equals("food")) {
                foodCounter++;
            }
        }
    }

    public void consume(Consumable con) {

    }


    public void removeItem(Equipment nameOfItem) {

    }


    public void addItem(Equipment nameOfItem) {

    }

    private void createItems() {//Here all the Items will be created and pud in a general arraylist that will then later be used in events and other parts of the game where all the items should be availible.
        allItems.add(new Equipment("gasmask"));
        allItems.add(new Equipment("rifle"));
        allItems.add(new Equipment("sledgeHammer"));
        allItems.add(new Equipment("medKit"));
        allItems.add(new Equipment("map"));
        allItems.add(new Equipment("radio"));
        allItems.add(new Equipment("bugspray"));
        allItems.add(new Equipment("flashlight"));
        allItems.add(new Equipment("padlock"));
        allItems.add(new Equipment("blowtorch"));
        allItems.add(new Consumable("food"));
        allItems.add(new Consumable("water"));
    }

    public void createInventory() {//Items are added to the start inventory that the game begins with
        int itemAdder = 6;

        while (itemAdder >= 0) {
            int num = rnd.nextInt(allItems.size());
            for (int i = 0; i < allItems.size(); i++) {
                if (num == i && checkIfExists(allItems.get(i))) {
                    items.add(allItems.get(i));
                }
            }
            itemAdder--;
        }

        items.add(new Consumable("water"));
        items.add(new Consumable("water"));
        items.add(new Consumable("food"));
        items.add(new Consumable("food"));

    }

    private Boolean checkIfExists(Item item) {//Duplicates will not be allowed in the inventory
        for (int i = 0; i < items.size(); i++) {
            if (Objects.equals(item.name, items.get(i).name) && !item.repeatable) {
                return false;
            }

        }
        return true;
    }


}
