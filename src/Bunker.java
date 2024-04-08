import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bunker {
    public ArrayList<Character> human = new ArrayList<Character>();
    public int day;
    final int MAX_CHARACTERS = 4;
    public Inventory inventory;
    String fileName = "src/Names.txt";


    public Bunker() {
        ArrayList<String> names = readFromFile(fileName);
        giveName(names);
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

    private void giveName(ArrayList<String> names){
        double rndEvent = Math.random();


    }


}
