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
        characterCreate(giveName(names), input());

        while(!human.isEmpty()) {
            newDay(day);
        }

        System.out.println("the end");
    }

    public void newDay(int currentDay){//The happenings of each day will be coded here
        String EventFile = "src/Events.txt";
        readFromFile(EventFile);
        dayInfo();

        endDay();
    }

    private void endDay() {//readying the characters for the next day
        day++;
        for (int i = 0; i <= human.size(); i++){
            human.get(i).hunger++;
            human.get(i).thirst++;
            if (human.get(i).sick > 0);{
                human.get(i).sick ++;
            }
            if (human.get(i).crazy > 0);{
                human.get(i).crazy ++;
            }
            if (human.get(i).hurt > 0);{
                human.get(i).hurt ++;
            }

        }
    }

    ;

    public void dayInfo(){// writes out the stats of each character for the user to interpret
        for (int i = 0; i <= human.size(); i++){
            if(human.get(i).hunger >= 14){
                System.out.println(human.get(i).name + " starved to death, rats eating away at their corpse.");
                human.remove(i);
            } else if (human.get(i).hunger >= 12) {
                System.out.println(human.get(i).name + " is starving, they have one foot in the grave.");
                //fix so that the food will heal less

            } else if (human.get(i).hunger >= 7) {
                System.out.println(human.get(i).name + " is hungry");

            }
            if(human.get(i).thirst >= 10){
                System.out.println(human.get(i).name + " dried out to death, rats eating away at their corpse.");
                human.remove(i);
            } else if (human.get(i).thirst >= 8) {
                System.out.println(human.get(i).name + " is parched, they have one foot in the grave.");
                //fix so that the food will heal less

            } else if (human.get(i).thirst >= 5) {
                System.out.println(human.get(i).name + " is thirsty");

            }

        }

    };

    public void prepExp(){

    };

    public int characterStatus(){
return 0;
    };

    private ArrayList<String> readFromFile(String fileName) {//reading from files to use repeatable info
        ArrayList<String> names = new ArrayList<String>();
        try {
            File file = new File (fileName);
            Scanner myFileScanner = new Scanner(file);
            while (myFileScanner.hasNextLine()) {
                names.add(myFileScanner.nextLine());

            }
        }catch(IOException e){
            System.out.println("File not found");
        }
        return names;
    }

    private String giveName(ArrayList<String> names){//Randomize the names given to characters for every run
        int nameIndx = rnd.nextInt(names.size());
        String chaName = names.get(nameIndx);
        names.remove(nameIndx);
        return chaName;
    }

    private void characterCreate(String name, int quantity){//I create the characters that the game will follow
        human.add(new Character(name));
        System.out.println(human.get(0).name);

    }

    private int input(){ // !!BROKEN!! Säger till så att man inte lägger in fel antal människor i sitt game
        int quantity = 5;
            do {
                try {
                    System.out.println("Write in numbers the number of characters you would like to start the game with (Not more than 4): ");
                    quantity = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Please, write in numbers 1 - 4");
                    break;
                }
            } while (quantity > MAX_CHARACTERS);
        return quantity;
    }




}
