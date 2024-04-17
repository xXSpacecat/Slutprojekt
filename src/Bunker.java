import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Bunker {
    public Scanner scan = new Scanner(System.in);
    public ArrayList<Character> human = new ArrayList<Character>();
    private int day = 1;
    final int MAX_CHARACTERS = 4;
    public Inventory inventory;
    private String fileName = "src/Names.txt";
    public Random rnd = new Random();


    public Bunker() {//The main game will be coded here
        ArrayList<String> names = readFromFile(fileName);
        characterCreate(input());

        while (!human.isEmpty()) {
            newDay(day);
        }

        System.out.println("the end");
    }

    public void newDay(int currentDay) {//The happenings of each day will be coded here
        String EventFile = "src/Events.txt";
        readFromFile(EventFile);
        dayInfo();

        endDay();
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


}
