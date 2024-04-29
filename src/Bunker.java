import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Bunker {
    public Scanner scan = new Scanner(System.in);
    public ArrayList<Character> humanInBunker = new ArrayList<Character>();
    public ArrayList<Character> humans = new ArrayList<Character>();
    private int day = 1;
    final int MAX_CHARACTERS = 4;
    public Inventory inventory = new Inventory();
    private String fileName = "src/Names.txt";
    private Random rnd = new Random();

    private Expedition expedition;


    public Bunker() {//The main game will be coded here
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
        System.out.println("DAY : " + day);
        String EventFile = "src/Events.txt";
        readFromFile(EventFile);
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
                humanInBunker.add(character);
                System.out.println(character.name + " is back from the expedition!");
                ArrayList<Item> newItems = expedition.foundItems();
                for (int i = 0; i < newItems.size(); i++) {
                    if (!inventory.items.contains(newItems.get(i))) {
                        inventory.items.add(newItems.get(i));
                    }
                }
                inventory.items.add(new Consumable("water"));
                inventory.items.add(new Consumable("food"));
                inventory.counter();
                expedition = null;
            } else {
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

    private void currentSupplies() {// Tell the user what supplies it has in the bunker each day

        System.out.println("The bunker currently has: ");
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
        for (int i = 0; i < humanInBunker.size(); i++) {
            if (humanInBunker.get(i).hunger >= 14) {
                System.out.println(humanInBunker.get(i).name + " starved to death, rats eating away at their corpse.");
                humansToRemove.add(humanInBunker.get(i));
            } else if (humanInBunker.get(i).hunger >= 12) {
                System.out.println(humanInBunker.get(i).name + " is starving, they have one foot in the grave.");
                //fix so that the food will heal less

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
        for (int i = 0; i < humansToRemove.size(); i++) {
            humanInBunker.remove(humansToRemove.get(i));
            humans.remove(humansToRemove.get(i));
        }
        scan.nextLine();

    }


    public void prepExp() {
        System.out.println("Do you want to go on an expedition? (y/n)");
        String exp = scan.next();
        while (true) {
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

    private int input() { //Säger till så att man inte lägger in fel antal människor i sitt game
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

                    } else if ((c > humanInBunker.size() + 1)) {
                        System.out.println("please stay within range of options.");
                    }
                }


            } catch (Exception e) {
                System.out.println("please write in number 1 - " + (humanInBunker.size() + 1));
                scan.nextLine();
            }
        }
    }

    private void goOnExp(Character character) {
        if (expedition == null) {
            System.out.println("do you want to send them off with any equipment?(y/n)");
            String wantsEquip = scan.next();
            while (true) {
                if (wantsEquip.equalsIgnoreCase("y")) {
                    expedition = new Expedition(character, inventory.getAllItems(), whatToEquip());
                    humanInBunker.remove(character);
                    break;
                } else if (wantsEquip.equalsIgnoreCase("n")) {
                    expedition = new Expedition(character, inventory.getAllItems());
                    break;
                } else {
                    System.out.println("please write 'y' for yes or 'n' for no.");
                }
            }


        }
    }

    private Equipment whatToEquip() {// by finding the owned equipment in items the characters can bring back more loot from expeditions
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
            try {
                equipmentChosen = scan.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please write in numbers 1 - " + num);
            }
        }
        return equipment.get(equipmentChosen);

    }


    public Inventory getInventory() {//Supplying the inventory to other classes
        return inventory;
    }

}
