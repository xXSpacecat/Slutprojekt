import java.util.ArrayList;

public class AgressiveExp extends Expedition {

    private String population;
    private String building;
    public String peopleRobbed = "";
    public int peopleKilled = 0;

    public AgressiveExp() {

    }

    private void robPeople(int aggressiveness) {
        mostItemsFound += 1;
        for (int i = 0; i <= 4; i++) {
            int killChance = rnd.nextInt(aggressiveness);
            if (killChance == 1) {
                killPerson();
            }
        }
    }

    public void killPerson() {
        peopleKilled += 1;
        if (rnd.nextInt(2) == 1) {
            mostItemsFound += 1;
        }
    }

    public void chooseToRob(int aggressiveness) {
        int robChance = rnd.nextInt(2);
        if (robChance == 1) {
            robPeople(aggressiveness);
        } else {
            peopleRobbed = "not";
        }
    }

    public void setPopulation(String population) {
        this.population = population;
    }


    public void setBuilding(String building) {
        this.building = building;
    }

    public void agrStory() {
        System.out.println("During " + getPerson().name + "'s expedition he found a " + building + ". Believing there should be resources inside " + getPerson().name + " went inside. The place was not abandon though, a bunch of " + population + " children still lived there. " + getPerson().name + " decided " + peopleRobbed + " to rob the " + population + " of their resources.");
        if (peopleKilled > 0) {
            System.out.println("The " + population + " found you lurking and became agressive and attacked you. In the ruckus " + getPerson().name + " killed " + peopleKilled + " " + population + " and managed to flee.");
        }
    }


}
