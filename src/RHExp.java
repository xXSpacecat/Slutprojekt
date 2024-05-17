import java.util.ArrayList;

public class RHExp extends AgressiveExp {
    private int aggressiveness = 5;

    Character character;

    public RHExp(Character character) {
        this.character = character;

    }

    public void gotSick() {//You can get sick in this place
        if (rnd.nextBoolean()) {
            getPerson().becomeSick();
        }
    }

    public int goOnRHExp() {
        dangerRate = rnd.nextInt(4, 6);
        setPopulation("Old people");
        setBuilding("Retirement Home");
        //gotSick();
        return placeInMostItems(chooseToRob(aggressiveness));
    }

}
