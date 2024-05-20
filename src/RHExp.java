public class RHExp extends AggressiveExp {//type of aggressive expedition
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

    public int goOnRHExp() {//will collect amount of most items found to be added when expedition comes to an end of the expedition of said type
        super.dangerRate = rnd.nextInt(4, 6);
        super.setPopulation("Old people");
        super.setBuilding("Retirement Home");
        //gotSick();
        return placeInMostItems(chooseToRob(aggressiveness));
    }

}
