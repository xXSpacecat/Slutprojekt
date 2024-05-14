import java.util.ArrayList;

public class AgressiveExp extends Expedition {

    private String population;

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

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }


}
