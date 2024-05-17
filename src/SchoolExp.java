import java.util.ArrayList;

public class SchoolExp extends AgressiveExp {
    private int aggressiveness = 3;

    public SchoolExp() {
        System.out.println("create school expedition");
    }

    public void drivenCrazy() {//School could drive one crazy
        if (rnd.nextBoolean()) {
            super.getPerson().becomeCrazy();
        }
    }

    public int goOnSchExp() {
        System.out.println("is here for real 2");
        super.dangerRate = rnd.nextInt(5, 7);
        super.setPopulation("Children");
        super.setBuilding("School");
        drivenCrazy();
        return placeInMostItems(chooseToRob(aggressiveness));
    }


}
