public class Consumable extends Item {

    public int satisfaction;


    public Consumable() {
    }

    public Consumable(String name) {
        this.name = name;
        super.repeatable = true;
    }
}
