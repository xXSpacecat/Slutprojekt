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
        for (int i = 0; i < 4; i++) {
            int killChance = rnd.nextInt(aggressiveness);
            if (killChance == 1) {
                itemsFound += killPerson();
            }
        }
        return itemsFound;
    }

    public int killPerson() {
        peopleKilled += 1;
        int itemsFound = 0;
        if (rnd.nextInt(2) == 1) {
            itemsFound += 1;
        }
        return itemsFound;
    }

    public int chooseToRob(int aggressiveness) {
        int robChance = rnd.nextInt(2);
        if (robChance == 1) {
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


    public String agrStory(Character character) {
        if (peopleKilled > 0) {
            return ("During " + character.name + "'s expedition he found a " + building + ". Believing there should be resources inside, " + character.name + " went inside. The place was not abandoned though; a bunch of " + population + " children still lived there. " + character.name + " decided " + peopleRobbed + " to rob the " + population + " of their resources. The " + population + " found you lurking and became aggressive and attacked you. In the ruckus, " + character.name + " killed " + peopleKilled + " " + population + " and managed to flee.");
        } else {
            return ("During " + character.name + "'s expedition he found a " + building + ". Believing there should be resources inside, " + character.name + " went inside. The place was not abandoned though; a bunch of " + population + " still lived there. " + character.name + " decided " + peopleRobbed + " to rob the " + population + " of their resources.");
        }
    }


}
