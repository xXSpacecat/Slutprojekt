import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Random;

public class Bunker {
    public Scanner scan = new Scanner(System.in);
    public ArrayList<Character> human = new ArrayList<Character>();
    private int day = 1;
    final int MAX_CHARACTERS = 4;
    public Inventory inventory = new Inventory();
    private String fileName = "src/Names.txt";
    public Random rnd = new Random();


    public Bunker() {//The main game will be coded here
        ArrayList<String> names = readFromFile(fileName);

        characterCreate(input());
        inventory.counter();
        while (!human.isEmpty()) {//As long as someone is alive a new day will begin
            newDay(day);
        }

        System.out.println("the end");
    }

    public void newDay(int currentDay) {//The happenings of each day will be coded here
        String EventFile = "src/Events.txt";
        readFromFile(EventFile);
        dayInfo();
        currentSupplies();
        maintenance();
        endDay();
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
        for (int i = 0; i < human.size(); i++) {
            human.get(i).sleep();
        }
    }

    ;

    public void dayInfo() {// writes out the stats of each character for the user to interpret
        for (int i = 0; i < human.size(); i++) {
            if (human.get(i).hunger >= 14) {
                System.out.println(human.get(i).name + " starved to death, rats eating away at their corpse.");
                human.remove(i);
            } else if (human.get(i).hunger >= 12) {
                System.out.println(human.get(i).name + " is starving, they have one foot in the grave.");
                //fix so that the food will heal less

            } else if (human.get(i).hunger >= 7) {
                System.out.println(human.get(i).name + " is hungry");

            }
            if (human.get(i).thirst >= 10) {
                System.out.println(human.get(i).name + " dried out to death, rats eating away at their corpse.");
                human.remove(i);
            } else if (human.get(i).thirst >= 8) {
                System.out.println(human.get(i).name + " is parched, they have one foot in the grave.");
                //fix so that the food will heal less

            } else if (human.get(i).thirst >= 5) {
                System.out.println(human.get(i).name + " is thirsty.");

            } else {
                System.out.println(human.get(i).name + " is feeling fine.");
            }

        }
        scan.nextLine();

    }

    ;

    public void prepExp() {

    }

    ;

    public int characterStatus() {
        return 0;
    }

    ;

    private ArrayList<String> readFromFile(String fileName) {//reading from files to use repeatable info
        ArrayList<String> names = new ArrayList<String>();
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
        String chaName = names.get(nameIndx);
        names.remove(nameIndx);
        return chaName;
    }

    private void characterCreate(int quantity) {//I create the characters that the game will follow

        if (quantity == 2) {
            human.add(new Character(giveName(readFromFile(fileName))));
            human.add(new Character(giveName(readFromFile(fileName))));
        } else if (quantity == 3) {
            human.add(new Character(giveName(readFromFile(fileName))));
            human.add(new Character(giveName(readFromFile(fileName))));
            human.add(new Character(giveName(readFromFile(fileName))));
        } else if (quantity == 4) {
            human.add(new Character(giveName(readFromFile(fileName))));
            human.add(new Character(giveName(readFromFile(fileName))));
            human.add(new Character(giveName(readFromFile(fileName))));
            human.add(new Character(giveName(readFromFile(fileName))));
        } else {
            human.add(new Character(giveName(readFromFile(fileName))));
        }
        for (int i = 0; i < human.size(); i++) {
            System.out.println(human.get(i).name);
        }
    }

    private int input() { //Säger till så att man inte lägger in fel antal människor i sitt game
        int quantity;
        do {
            try {
                System.out.println("Write in numbers the number of characters you would like to start the game with (Not more than 4): ");
                quantity = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                scan.nextLine();
                quantity = 10;
                System.out.println("Please, write in numbers 1 - 4");
            }
        } while (quantity > MAX_CHARACTERS || quantity < 1);
        return quantity;
    }

    private void maintenance() {//Here the characters will be taken care of
        boolean moveOn = false;
        while (!moveOn) {
            System.out.println("  1. Feed\n  2. Water\n  3. Heal\n  4. Next");
            int v = scan.nextInt();
            scan.nextLine();
            switch (v) {
                case 1:
                    feed();
                    break;
                case 2:
                    water();
                    break;
                case 3:
                    heal();
                    break;
                case 4:
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

    void whoToDo(String type) { //this will enter the singular characters needs
        int i;
        for (i = 0; i < human.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + human.get(i).name);
        }
        System.out.println((i + 1) + ". Back");
        int c = scan.nextInt();
        for (int j = 0; j < human.size(); j++) {
            if ((j + 1) == c) {
                if (type.equals("food")) {
                    human.get(j).eat(this);
                }
                if (type.equals("water")) {
                    human.get(j).drink(this);
                }
                if (type.equals("meds")) {
                    human.get(j).heal();
                }
            }

        }
    }

    public Inventory getInventory() {
        return inventory;
    }

}
