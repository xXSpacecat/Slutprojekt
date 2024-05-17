import java.util.ArrayList;

public class SchoolExp extends AgressiveExp {
    private int aggressiveness = 3;
    Character character;

    public SchoolExp(Character character) {
        this.character = character;
    }

    public void drivenCrazy() {//School could drive one crazy
        if (rnd.nextBoolean()) {
            super.getPerson().becomeCrazy();
        }
    }

    public int goOnSchExp() {
        super.dangerRate = rnd.nextInt(3, 7);
        super.setPopulation("Children");
        super.setBuilding("School");
        //drivenCrazy();
        return placeInMostItems(chooseToRob(aggressiveness));
    }

}
