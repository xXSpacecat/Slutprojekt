import java.io.File;
import java.io.IOException;
import java.util.*;

public class Bunker {//The game process happens in this bunker class
    public Scanner scan = new Scanner(System.in);
    public ArrayList<Character> humanInBunker = new ArrayList<Character>();
    public ArrayList<Character> humans = new ArrayList<Character>();
    private int day = 1;
    final int MAX_CHARACTERS = 4;
    public Inventory inventory = new Inventory();
    private String fileName = "src/Names.txt";
    private Random rnd = new Random();

    private Expedition expedition;


    public Bunker() {//The main game will be coded here, constructor
        ArrayList<String> names = readFromFile(fileName);
        System.out.println("Welcome to my Bunker game!");
        System.out.println("Here you will survive however long you can with the number of characters of your choice.");
        System.out.println("The game is not fully developed but is already playable.");
        System.out.println("You make your choices by inputting a number before the option of your choice or by writing 'y' for yes an 'n' for no.");
        System.out.println("To continue just press enter");
        scan.nextLine();
        characterCreate(input());
        inventory.counter();

        while (!humans.isEmpty()) {//As long as someone is alive a new day will begin
            newDay(day);
        }

        System.out.println("the end");
    }

    public void newDay(int currentDay) {//The happenings of each day will be coded here
        System.out.println("\n\n############\n\nDAY : " + day);
        dayInfo();
        currentSupplies();
        maintenance();
        checkExpedition();
        endDay();
    }

    private void checkExpedition() {//If the expedition is done the human will be brought back, If the user is tired of waiting he can himself end the expedition
        if (expedition != null) {
            Character character = expedition.checkExp();

            if (character != null) {
                bringBackCharacter(character);

            } else {//THe character can be killed off if user wants to and then expedition will be available again.
                System.out.println(expedition.getPerson().name + " has been out for " + expedition.daysOnExp + " days, Do you want to terminate expedition (" + expedition.getPerson().name + " will die if expedition is terminated) (y/n)");

                while (true) {
                    String killExp = scan.nextLine();
                    if (killExp.equalsIgnoreCase("y")) {
                        System.out.println(expedition.getPerson().name + " will not return from the expedition");
                        expedition = null;
                        break;
                    } else if (killExp.equalsIgnoreCase("n")) {
                        break;
                    } else {
                        System.out.println("please write 'y' for yes or 'n' for no.");
                    }
                }
            }
        } else {
            System.out.println("You have no current expedition.");
            prepExp();
        }
    }

    private void bringBackCharacter(Character character) {// The character will be brought back if not dead and what it brings will be added to inventory
        humanInBunker.add(character);
        System.out.println(character.name + " is back from the expedition!");
        System.out.println(expedition.getStory());

        ArrayList<Item> newItems = expedition.foundItems();
        for (int i = 0; i < newItems.size(); i++) {
            Item item = newItems.get(i);
            if (item instanceof Equipment) {
                if (!inventory.items.contains(item)) {
                    inventory.items.add(item);
                }
            } else if (item instanceof Consumable) {
                inventory.items.add(item);
            }
        }
        inventory.items.add(new Consumable("water"));
        inventory.items.add(new Consumable("food"));
        inventory.counter();
        expedition = null;
    }

    private void currentSupplies() {// Tell the user what supplies it has in the bunker each day

        System.out.println("\n*******\nThe bunker currently has: ");
        for (int i = 0; i < inventory.items.size(); i++) {
            if (!Objects.equals(inventory.items.get(i).name, "water") && !Objects.equals(inventory.items.get(i).name, "food")) {
                System.out.println("1 " + inventory.items.get(i).name);
                //fix so that it writes out if the thing is broken
            }
        }
        System.out.println(inventory.getFoodCounter() + " soups");
        System.out.println(inventory.getWaterCounter() + " waters");
    }


    private void endDay() {//readying the characters for the next day
        day++;
        for (int i = 0; i < humanInBunker.size(); i++) {
            humanInBunker.get(i).sleep();
        }
    }


    public void dayInfo() {// writes out the stats of each character for the user to interpret
        ArrayList<Character> humansToRemove = new ArrayList<>();
        showHungerAndThirst(humansToRemove);
        showCrazyAndHurt(humansToRemove);
        for (int i = 0; i < humansToRemove.size(); i++) {//characters killed from hunger thirst or anything else of the above will be removed from the game
            humanInBunker.remove(humansToRemove.get(i));
            humans.remove(humansToRemove.get(i));
        }
        scan.nextLine();

    }

    public void showHungerAndThirst(ArrayList<Character> humansToRemove) {//Shows the characters status
        for (int i = 0; i < humanInBunker.size(); i++) {
            if (humanInBunker.get(i).hunger >= 14) {
                System.out.println(humanInBunker.get(i).name + " starved to death, rats eating away at their corpse.");
                humansToRemove.add(humanInBunker.get(i));
            } else if (humanInBunker.get(i).hunger >= 12) {
                System.out.println(humanInBunker.get(i).name + " is starving, they have one foot in the grave.");

            } else if (humanInBunker.get(i).hunger >= 7) {
                System.out.println(humanInBunker.get(i).name + " is hungry");

            }
            if (humanInBunker.get(i).thirst >= 10) {
                System.out.println(humanInBunker.get(i).name + " dried out to death, rats eating away at their corpse.");
                humansToRemove.add(humanInBunker.get(i));
            } else if (humanInBunker.get(i).thirst >= 8) {
                System.out.println(humanInBunker.get(i).name + " is parched, they have one foot in the grave.");

            } else if (humanInBunker.get(i).thirst >= 5) {
                System.out.println(humanInBunker.get(i).name + " is thirsty.");

            } else {
                System.out.println(humanInBunker.get(i).name + " is feeling fine.");
            }

        }
    }

    public void showCrazyAndHurt(ArrayList<Character> humansToRemove) {//Shows the characters status
        for (int i = 0; i < humanInBunker.size(); i++) {
            if (humanInBunker.get(i).crazy >= 14) {
                System.out.println(humanInBunker.get(i).name + " got too crazy and in search for an adventure left the bunker, not to come back.");
                humansToRemove.add(humanInBunker.get(i));
            } else if (humanInBunker.get(i).crazy >= 12) {
                System.out.println(humanInBunker.get(i).name + " is delusional, they begin to sing in their sleep.");

            } else if (humanInBunker.get(i).crazy >= 7) {
                System.out.println(humanInBunker.get(i).name + " is crazy");

            }
            if (humanInBunker.get(i).sick >= 10) {
                System.out.println(humanInBunker.get(i).name + " coughed until they could coughed no more, their corpse rotting away in a corner.");
                humansToRemove.add(humanInBunker.get(i));
            } else if (humanInBunker.get(i).sick >= 8) {
                System.out.println(humanInBunker.get(i).name + " is fatigued, they have a hard time rising to their feet.");

            } else if (humanInBunker.get(i).sick >= 5) {
                System.out.println(humanInBunker.get(i).name + " is sick");

            }
            if (humanInBunker.get(i).hurt >= 10) {
                System.out.println(humanInBunker.get(i).name + " bled to death, a pool of blood lies fresh in the bunker.");
                humansToRemove.add(humanInBunker.get(i));
            } else if (humanInBunker.get(i).hurt >= 8) {
                System.out.println(humanInBunker.get(i).name + " is looking rather pale, their wound has yet too heal.");

            } else if (humanInBunker.get(i).hurt >= 5) {
                System.out.println(humanInBunker.get(i).name + " is hurt");

            }

        }
    }


    public void prepExp() {//Here the player can choose to go on an expedition
        System.out.println("Do you want to go on an expedition? (y/n)");
        while (true) {
            String exp = scan.next();
            if (exp.equalsIgnoreCase("y")) {
                System.out.println("Who would you like to send?");
                whoToDo("exp");
                break;
            } else if (exp.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("please write 'y' for yes or 'n' for no.");
            }
        }
    }


    private ArrayList<String> readFromFile(String fileName) {//reading from files to use repeatable info
        ArrayList<String> names = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner myFileScanner = new Scanner(file);
            while (myFileScanner.hasNextLine()) {
                names.add(myFileScanner.nextLine());

            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return names;
    }

    private String giveName(ArrayList<String> names) {//Randomize the names given to characters for every run
        int nameIndx = rnd.nextInt(names.size());
        return names.get(nameIndx);
    }

    private void characterCreate(int quantity) {//I create the characters that the game will follow
        ArrayList<String> names = (readFromFile(fileName));
        String name;

        for (int i = 0; i < quantity; i++) {
            name = giveName(names);
            humanInBunker.add(new Character(name));
            names.remove(name);
        }
        humans = humanInBunker;
        for (int i = 0; i < humanInBunker.size(); i++) {
            System.out.println(humanInBunker.get(i).name);
        }
    }

    private int input() { //Making so that the right amount of people are added to the game
        int quantity;
        do {
            try {
                System.out.println("Write in numbers the number of characters you would like to start the game with (Not more than " + MAX_CHARACTERS + "): ");
                quantity = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                scan.nextLine();
                quantity = (MAX_CHARACTERS + 1);
                System.out.println("Please, write in numbers 1 - 4");
            }
        } while (quantity > MAX_CHARACTERS || quantity < 1);
        return quantity;
    }

    private void maintenance() {//Here the characters will be taken care of
        boolean moveOn = false;
        while (!moveOn) {
            System.out.println("  1. Feed\n  2. Water\n  3. Heal\n  4. Next");
            String v = scan.nextLine();
            switch (v) {
                case "1":
                    feed();
                    break;
                case "2":
                    water();
                    break;
                case "3":
                    heal();
                    break;
                case "4":
                    moveOn = true;
                    break;

                default:
                    System.out.println("please write in numbers 1 to 4");
                    break;

            }
        }
    }

    private void water() {// This will start the process of feeding characters
        System.out.println("Who would you like to water?");
        String type = "water";
        whoToDo(type);
    }

    public void feed() {// This will start the process of watering characters
        System.out.println("Who would you like to feed?");
        String type = "food";
        whoToDo(type);
    }

    public void heal() {// This will start the process of watering characters
        System.out.println("Who would you like to heal?");
        String type = "meds";
        whoToDo(type);
    }

    public void whoToDo(String type) { //this will enter the singular characters needs
        int i;
        boolean done = false;
        while (!done) {
            for (i = 0; i < humanInBunker.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + humanInBunker.get(i).name);
            }
            System.out.println((i + 1) + ". Back");
            try {
                int c = scan.nextInt();
                scan.nextLine();
                if ((humanInBunker.size() + 1) == c) {
                    done = true;
                    break;
                }
                for (int j = 0; j < humanInBunker.size(); j++) {
                    if ((j + 1) == c) {
                        if (type.equals("food")) {
                            humanInBunker.get(j).eat(this);
                            break;
                        }
                        if (type.equals("water")) {
                            humanInBunker.get(j).drink(this);
                            break;
                        }
                        if (type.equals("meds")) {
                            humanInBunker.get(j).heal(this);
                            break;
                        }
                        if (type.equals("exp")) {
                            goOnExp(humanInBunker.get(j));
                            done = true;
                            break;
                        }

                    } else if ((c > humanInBunker.size() + 1) || c <= 0) {
                        System.out.println("please stay within range of options.");
                    }
                }
            } catch (Exception e) {
                System.out.println("please write in number 1 - " + (humanInBunker.size() + 1));
                scan.nextLine();
            }
        }
    }

    private void goOnExp(Character character) {// the user has chosen to go on expedition, here you chose if you want an equipment
        if (expedition == null) {
            System.out.println("do you want to send them off with any equipment?(y/n)");
            while (true) {
                String wantsEquip = scan.next();
                if (wantsEquip.equalsIgnoreCase("y")) {
                    Equipment equip = whatToEquip();
                    expedition = new Expedition(character, inventory.getAllItems(), equip);
                    System.out.println(character.name + " went out to explore with a " + equip.name);
                    expedition = new Expedition(character, inventory.getAllItems(), equip);
                    humanInBunker.remove(character);
                    inventory.items.remove(equip);
                    break;
                } else if (wantsEquip.equalsIgnoreCase("n")) {
                    expedition = new Expedition(character, inventory.getAllItems());
                    System.out.println(character.name + " went out to explore.");
                    humanInBunker.remove(character);
                    break;
                } else {
                    System.out.println("please write 'y' for yes or 'n' for no.");
                }
            }
        }
    }


    private Equipment whatToEquip() {//Any equipment found in the inventory can be used in an expedition, does nothing yet but can be implemented
        System.out.println(" What would you like to equip?");
        int num = 0;
        ArrayList<Equipment> equipment = new ArrayList<>();
        for (int i = 0; i < inventory.items.size(); i++) {
            if (inventory.items.get(i) instanceof Equipment) {
                num++;
                System.out.println("  " + (num) + ". " + inventory.items.get(i).name);
                equipment.add((Equipment) inventory.items.get(i));
            }
        }
        int equipmentChosen;
        while (true) {
            scan.nextLine();
            try {
                equipmentChosen = scan.nextInt();
                if (equipmentChosen > equipment.size() || equipmentChosen <= 0) {
                    System.out.println("please write in numbers within range of options");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please write in numbers 1 - " + num);
                scan.nextLine();
            }
        }
        return equipment.get(equipmentChosen - 1);

    }

    public Inventory getInventory() {//Supplying the inventory to other classes
        return inventory;
    }

}
