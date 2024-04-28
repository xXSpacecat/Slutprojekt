public class Equipment extends Item {


    public boolean broken;

    public Equipment() {
    }

    public Equipment(String name) {
        this.name = name;
        super.repeatable = false;
    }


}
