import java.util.ArrayList;

public class SchoolExp extends AgressiveExp {
    private int aggressiveness = 3;

    public SchoolExp() {
        dangerRate = rnd.nextInt(5, 7);
        setPopulation("Children");
        chooseToRob(aggressiveness);

    }
}
