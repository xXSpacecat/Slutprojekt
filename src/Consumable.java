public class Consumable extends Item {//special type of item, consists of food and water and what can be consumed


    public Consumable() {//constructor
    }

    public Consumable(String name) {//constructor
        this.name = name;
        super.repeatable = true;
    }


}
