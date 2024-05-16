public class RHExp extends AgressiveExp {
    private int aggressiveness = 5;

    public RHExp() {
        dangerRate = rnd.nextInt(5, 7);
        setPopulation("Old people");
        setBuilding("Retirement Home");
        chooseToRob(aggressiveness);
        gotSick();

    }

    public void gotSick() {//You can get sick in this place
        if (rnd.nextBoolean()) {
            getPerson().becomeSick();
        }
    }

}
