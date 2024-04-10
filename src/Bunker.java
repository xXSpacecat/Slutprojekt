import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Bunker {
    public Scanner scan = new Scanner(System.in);
    public ArrayList<Character> human = new ArrayList<Character>();
    public int day;
    final int MAX_CHARACTERS = 4;
    public Inventory inventory;
    String fileName = "src/Names.txt";
    public Random rnd = new Random();


    public Bunker() {//The main game will be coded here
        ArrayList<String> names = readFromFile(fileName);

        characterCreate(giveName(names), input());
        System.out.println(human);
    }

    public void newDay(){

    };

    public void dayInfo(){

    };

    public void prepExp(){

    };

    public int characterStatus(){
return 0;
    };

    private ArrayList<String> readFromFile(String fileName) {
        //reading from files to use repeatable info
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
        System.out.println(names);
        return names;
    }

    private String giveName(ArrayList<String> names){
        //Randomize the names given to characters for every run
        int nameIndx = rnd.nextInt(names.size());
        String chaName = names.get(nameIndx);
        names.remove(nameIndx);
        return chaName;
    }

    private void characterCreate(String name, int quantity){
        //I create the characters that the game will follow
        human.add(new Character(name));
        System.out.println(human.get(0).name);

    }

    private int input(){ // !!BROKEN!! Säger till så att man inte lägger in fel antal människor i sitt game
        int quantity = 0;
            do {
                try {
                    System.out.println("Write in numbers the number of characters you would like to start the game with (Not more than 4): ");
                    quantity = scan.nextInt();
                } catch (Exception e) {
                    System.out.println("Please, write in numbers 1 - 4");
                }
            } while (quantity > MAX_CHARACTERS);
        return quantity;
    }




}
