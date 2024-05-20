abstract public class AggressiveExp extends Expedition {//aggressive expeditions should be expeditions that offer more loot than other types of expeditions, have other storylines as well as it is more dangerous.

    private String population;
    private String building;
    public String peopleRobbed = "";
    public int peopleKilled = 0;

    public AggressiveExp() {//constructor

    }


    private int robPeople(int aggressiveness) {//amount of items that can be found will rise and an opportunity to kill for more loot as well
        int itemsFound = 1;
        for (int i = 0; i < 4; i++) {
            int killChance = rnd.nextInt(aggressiveness);
            if (killChance == 1) {
                itemsFound += killPerson();
            }
        }
        return itemsFound;
    }

    public int killPerson() {//The action of killing someone for more loot, can be repeated for even more loot
        peopleKilled += 1;
        int itemsFound = 0;
        if (rnd.nextInt(2) == 1) {
            itemsFound += 1;
        }
        return itemsFound;
    }

    public int chooseToRob(int aggressiveness) {//The rob opportunity will be 50/50 and from there it will either go to other methods for amount of added foundedItems or not.
        int robChance = rnd.nextInt(2);
        if (robChance == 1) {
            return (robPeople(aggressiveness));
        } else {
            peopleRobbed = "not";
        }
        return (0);
    }

    public String agrStory(Character character) {//Story as a string will be produced depending on which methods in this class the code runs through
        if (peopleKilled > 0) {
            return ("During " + character.name + "'s expedition he found a " + building + ". Believing there should be resources inside, " + character.name + " went inside. The place was not abandoned though; a bunch of " + population + " still lived there. " + character.name + " decided " + peopleRobbed + " to rob the " + population + " of their resources. The " + population + " found you lurking and became aggressive and attacked you. In the ruckus, " + character.name + " killed " + peopleKilled + " " + population + " and managed to flee.");
        } else {
            return ("During " + character.name + "'s expedition he found a " + building + ". Believing there should be resources inside, " + character.name + " went inside. The place was not abandoned though; a bunch of " + population + " still lived there. " + character.name + " decided " + peopleRobbed + " to rob the " + population + " of their resources.");
        }
    }

    public void setPopulation(String population) {//Setter
        this.population = population;
    }


    public void setBuilding(String building) {//setter
        this.building = building;
    }


}
