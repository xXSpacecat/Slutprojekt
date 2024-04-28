public class Character {

    public String name;
    public boolean onExp;
    private Item equipped;
    public int hunger = 0;
    public int thirst = 0;
    public int crazy = 0;
    public int hurt = 0;
    public int sick = 0;
    private boolean isFed = false;
    private boolean isWatered = false;


    public Character() {
    }

    public Character(String name) {
        this.name = name;
    }

    public void goOnExp(Equipment inUse) {

    }

    ;

    public void sleep() {
        this.hunger++;
        this.thirst++;
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

    /* public void eat() {

     }

     ;*/
    public void eat(Bunker bunker) {// Decrease character's hunger by first checking the amount of food in the bunker
        Inventory bunkerInventory = bunker.getInventory();
        int foodCount = bunkerInventory.getFoodCounter();

        if (foodCount > 0) {

            if (this.hunger <= 7) {
                this.hunger = 0;
            }
            if (this.hunger <= 12) {
                this.hunger -= 4;
            }

            bunkerInventory.consumeFood();

            System.out.println(name + " eats from the bunker.");
        } else {
            System.out.println("There's not enough food in the bunker!");
        }
    }

    public void drink(Bunker bunker) {// Decrease character's thisrt by first checking the amount of food in the bunker
        Inventory bunkerInventory = bunker.getInventory();
        int foodCount = bunkerInventory.getWaterCounter();

        if (foodCount > 0) {

            if (this.thirst <= 5) {
                this.thirst = 0;
            }
            if (this.thirst <= 10) {
                this.thirst -= 2;
            }

            bunkerInventory.consumeWater();

            System.out.println(name + " drinks from the bunker.");
        } else {
            System.out.println("There's not enough water in the bunker!");
        }
    }

    public void heal() {

    }

    ;

    public void die() {


    }

    ;


}
