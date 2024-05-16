import java.util.ArrayList;

public class SchoolExp extends AgressiveExp {

    public String
    private int aggressiveness = 3;

    public SchoolExp() {
        dangerRate = rnd.nextInt(5, 7);
        setPopulation("Children");
        chooseToRob(aggressiveness);
        drivenCrazy();

    }

    public void drivenCrazy() {//School could drive one crazy
        if (rnd.nextBoolean()) {
            getPerson().becomeCrazy();
        }
    }

    public void schoolStory() {
        System.out.println("During " + getPerson().name + "'s expedition he found a school ");
    }

}
