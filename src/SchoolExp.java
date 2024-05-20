public class SchoolExp extends AggressiveExp {//Type of aggressive expedition
    private int aggressiveness = 3;
    Character character;

    public SchoolExp(Character character) {//constructor
        this.character = character;
    }

    public void drivenCrazy() {//School could drive one crazy
        if (rnd.nextBoolean()) {
            super.getPerson().becomeCrazy();
        }
    }

    public int goOnSchExp() {//this method begins a row of methods to find most items to be found in the expedtition
        super.dangerRate = rnd.nextInt(3, 7);
        super.setPopulation("Children");
        super.setBuilding("School");
        //drivenCrazy();
        return placeInMostItems(chooseToRob(aggressiveness));
    }

}
