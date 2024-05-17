import java.util.ArrayList;

public class RHExp extends AgressiveExp {
    private int aggressiveness = 5;

    public RHExp() {
        System.out.println("create RH expedition");
    }

    public void gotSick() {//You can get sick in this place
        if (rnd.nextBoolean()) {
            getPerson().becomeSick();
        }
    }

    public int goOnRHExp() {
        System.out.println("is here for real");
        dangerRate = rnd.nextInt(6, 8);
        setPopulation("Retirees");
        setBuilding("Retirement Home");
        gotSick();
        return placeInMostItems(chooseToRob(aggressiveness));
    }

}
