public class Character {

    public String name;
    public boolean onExp;
    private Item equipped;
    public int hunger = 0;
    public int thirst = 0;
    public int crazy = 0;
    public int hurt = 0;
    public int sick = 0;


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

    public void eat() {

    }

    ;

    public void drink() {

    }

    ;

    public void heal() {

    }

    ;

    public void die() {


    }

    ;


}
