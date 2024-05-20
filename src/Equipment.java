public class Equipment extends Item {//Items that can be equipped on expeditions

    public boolean broken;

    public Equipment() {//constructor
    }

    public Equipment(String name) {//constructor
        this.name = name;
        super.repeatable = false;
    }


}
