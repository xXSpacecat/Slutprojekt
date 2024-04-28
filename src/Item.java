import java.util.Objects;
import java.util.Random;

public abstract class Item {
    public String name;
    protected Boolean repeatable;

    public String itemStatus = " ";

    public Random rnd = new Random();

    public Item() {
    }

    public void use() {
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

    ;


}

