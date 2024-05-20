import java.util.Objects;
import java.util.Random;

public abstract class Item {//The skeleton of all items
    public String name;
    protected Boolean repeatable;

    public String itemStatus = " ";

    private Random rnd = new Random();

    public Item() {//constructor
    }

    public void use() {// objects will have a chance at braking after every use, medkits can only be used once
        if (Objects.equals(this.name, "medKit")) {
            this.itemStatus = "empty";
        } else {
            final int BREAK_CHANCE = 3;
            int chance = rnd.nextInt(BREAK_CHANCE);
            if (chance == 1) {
                this.itemStatus = "broken";
            }
        }
    }


}

