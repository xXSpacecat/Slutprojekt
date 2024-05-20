import java.util.Objects;
import java.util.Random;

public class Character {//The character the game is created around

    public String name;
    public int hunger = 0;
    public int thirst = 0;
    public int crazy = 0;
    public int hurt = 0;
    public int sick = 0;
    private boolean isFed = false;
    private boolean isWatered = false;
    private Random rnd = new Random();


    public Character() {//constructor
    }

    public Character(String name) {//constructor
        this.name = name;
    }


    public void sleep() {//Characters stats are changed for the next day
        this.hunger++;
        this.thirst++;
        this.isFed = false;
        this.isWatered = false;
        if (this.sick > 0) {
            this.sick++;
        }
        if (this.crazy > 0) {
            this.crazy++;
        }
        if (this.hurt > 0) {
            this.hurt++;
        }
    }

    public void eat(Bunker bunker) {// Decrease character's hunger by first checking the amount of food in the bunker
        Inventory bunkerInventory = bunker.getInventory();
        int foodCount = bunkerInventory.getFoodCounter();
        if (!isFed) {
            if (foodCount > 0) {
                if (this.hunger <= 7) {
                    this.hunger = 0;
                }
                if (this.hunger <= 12) {
                    this.hunger -= 4;
                }

                bunkerInventory.consumeFood();
                System.out.println(name + " eats from the bunker.");
                isFed = true;
            } else {
                System.out.println("There's not enough food in the bunker!");
            }
        } else {
            System.out.println("They have already eaten");
        }
    }

    public void drink(Bunker bunker) {// Decrease character's thirst by first checking the amount of food in the bunker
        Inventory bunkerInventory = bunker.getInventory();
        int foodCount = bunkerInventory.getWaterCounter();

        if (!isWatered) {
            if (foodCount > 0) {

                if (this.thirst <= 5) {
                    this.thirst = 0;
                }
                if (this.thirst <= 10) {
                    this.thirst -= 2;
                }

                bunkerInventory.consumeWater();
                isWatered = true;
                System.out.println(name + " drinks from the bunker.");
            } else {
                System.out.println("There's not enough water in the bunker!");
            }
        } else {
            System.out.println("They drank earlier");
        }
    }

    public void heal(Bunker bunker) {// resets characters other stats if there is a medkit in the bunker inventory
        Inventory bunkerInventory = bunker.getInventory();

        boolean hasMedKit = false;
        for (int i = 0; i < bunkerInventory.items.size(); i++) {
            if (Objects.equals(bunkerInventory.items.get(i).name, "medKit")) {
                hasMedKit = true;
                break;
            }

        }
        if (hasMedKit) {
            this.hurt = 0;
            this.sick = 0;
            this.crazy = 0;
            bunkerInventory.useItem("medkit");

            System.out.println(name + " is healed.");
        } else {
            System.out.println("You don't own a medKit");
        }
    }

    public void becomeCrazy() {//If a character does something that can make them crazy this method should be called
        if (rnd.nextBoolean()) {
            this.crazy += 1;
        } else {
            this.crazy += 2;
        }
    }

    public void becomeHurt() {//If a character does something that can make them hurt this method should be called
        if (rnd.nextBoolean()) {
            this.crazy += 1;
        } else {
            this.crazy += 2;
        }
    }

    public void becomeSick() {//If a character does something that can make them sick this method should be called
        if (rnd.nextBoolean()) {
            this.crazy += 1;
        } else {
            this.crazy += 2;
        }
    }


}
