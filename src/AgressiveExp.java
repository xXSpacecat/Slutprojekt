import java.util.ArrayList;

public class AgressiveExp extends Expedition {

    public AgressiveExp() {

    }

    private String population;
    private String building;
    public String peopleRobbed = "";
    public int peopleKilled = 0;

    private int robPeople(int aggressiveness) {
        int itemsFound = 1;
        System.out.println("items found_ " + itemsFound);
        for (int i = 0; i < 4; i++) {
            int killChance = rnd.nextInt(aggressiveness);
            if (killChance == 1) {
                itemsFound += killPerson();
                System.out.println("Killed items" + itemsFound);
            }
        }
        System.out.println(itemsFound);
        return itemsFound;
    }

    public int killPerson() {
        peopleKilled += 1;
        int itemsFound = 0;
        if (rnd.nextInt(2) == 1) {
            itemsFound += 1;
            System.out.println("items found_:) " + itemsFound);
        }
        return itemsFound;
    }

    public int chooseToRob(int aggressiveness) {
        System.out.println("Choses to rob :))");
        int robChance = rnd.nextInt(2);
        System.out.println(robChance);
        if (robChance == 1) {
            System.out.println("has robbebdibobed");
            return (robPeople(aggressiveness));
        } else {
            peopleRobbed = "not";
        }
        return (0);
    }

    public void setPopulation(String population) {
        this.population = population;
    }


    public void setBuilding(String building) {
        this.building = building;
    }


    public String agrStory() {
        if (peopleKilled > 0) {
            return ("During " + getPerson().name + "'s expedition he found a " + building + ". Believing there should be resources inside " + getPerson().name + " went inside. The place was not abandon though, a bunch of " + population + " children still lived there. " + getPerson().name + " decided " + peopleRobbed + " to rob the " + population + " of their resources." + "The " + population + " found you lurking and became agressive and attacked you. In the ruckus " + getPerson().name + " killed " + peopleKilled + " " + population + " and managed to flee.");
        } else {
            return ("During " + getPerson().name + "'s expedition he found a " + building + ". Believing there should be resources inside " + getPerson().name + " went inside. The place was not abandon though, a bunch of " + population + " children still lived there. " + getPerson().name + " decided " + peopleRobbed + " to rob the " + population + " of their resources.");
        }
    }


}
