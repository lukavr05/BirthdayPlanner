import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ActivityGenerator {
    private List<String> list;
    private Random rand;
    private boolean hasRestaurant;
    private String previous;
    
    ActivityGenerator() {
            rand = new Random();
            this.list = new ArrayList<String>();
            this.hasRestaurant = false;
        }
    
    public boolean getRestaurant() {
        return this.hasRestaurant;
    }

    public String getPrevious(int index) {
        if (this.list.size() > 1) {
            return this.list.get(index-1);
        } else {
            return null;
        }
    }
    
    public void chooseMain(int index) {
        if (this.getPrevious(index) != null) {

        }
    }

    public void chooseEating(boolean choose) {
        if (!this.getRestaurant() && choose) {
            list.add("")
        }
    }

    public void chooseRandom(boolean main) {
        if (main) {
            list.add("main");
        } else {
            chooseEating();
        }
    }

    public void chooseRandomMain() {
        if (rand.nextBoolean()) {
            list.add()
        }
    }

}

