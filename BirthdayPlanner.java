import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class BirthdayPlanner {
    private ActivityStore mains;
    private RestaurantStore restaurants;
    private CafeStore cafes;
    private int prefix;
    private boolean hasRestaurant;
    private ArrayList<String> track;
    private List<String> list;
    private Random rand;

    BirthdayPlanner(int prefix) throws IOException{
        // STORES
        mains = new ActivityStore("main-activities.txt", prefix);
        restaurants = new RestaurantStore(prefix);
        cafes = new CafeStore(prefix);
        
        // USEFUL TRACKERS
        this.prefix = prefix;
        hasRestaurant = false;
        track = new ArrayList<String>();
        list = new ArrayList<String>();
        rand = new Random();
    }

    // choose the next activity based on the pre-defined conditions
    public void chooseNext(String s, int index){
        String previous = "";
        if (track.size() >= 1) {
            previous = track.get(index);
        } 

        // if we have two mains in a row me must generate an eating activity
        if (track.size() >= 2 && (previous.equals("main")) && (track.get(index-1).equals(previous))) {
            addEating(s);

        // if the previous activity was an eating activity we must have a main activity
        } else if (previous.equals("eating")) {
            addMain(s);

        // under any other condition just choose randomly between the two
        } else {
            chooseRandom(s);
        }
    }

    // choose a random activity 
    public void chooseRandom(String s) {
        if (rand.nextBoolean()) {
            addMain(s);
        } else {
            addEating(s);
        }
    }

    // add a main activity
    public void addMain(String s){
        String a = mains.getRandomItem(s);
        if (a != null) {
            track.add("main");
            list.add(a);
        }
    }

    // add an eating activity
    public void addEating(String s) {
        String a = null;

        // randomly choose if there is no restaurant
        if (rand.nextBoolean() && !hasRestaurant) {
            a = restaurants.getRandomItem(s);
            if (a != null) {
                track.add("eating");
                list.add(a);
                hasRestaurant = true;
            } else {
                addCafe(s);
            }
        } else {
            addCafe(s);
        }
    }

    // add a cafe
    public void addCafe(String s) {
        String a = cafes.getRandomItem(s);
        if (a != null) {
            track.add("eating");
            list.add(a);
        }
    }

    // check if we added something by comparing the size of track before and after a pass of chooseNext()
    public boolean checkAdded(int before) {
        return (track.size() > before);
    }

    // generate the list to be output
    public List<String> generate(String input) {
        int index = -1;
        while (input.length() > 0) {
            boolean added = false;
            int prefixLength = Math.min(rand.nextInt(prefix) + 1, input.length());
            int trackSizeBefore = track.size();
            // while we havent added something... keep trying to!
            while (!added) {
                String s = input.substring(0,prefixLength);
                chooseNext(s, index);
                if (checkAdded(trackSizeBefore)) {
                    input = input.substring(prefixLength);
                    added = true;
                } else {
                    prefixLength--;
                }
            }
            // once we add something the tracking index can be incremented
            index++;
        }
        return list;
    }

    // return the list as a string by iterating through
    public String toString(List<String> list) {
        String s = "";
        for (String item : list) {
            s += item + "\n";
        }
        return s;
    }

    public static void main(String[] args) throws IOException{
        String input = null;
        int prefix = 0;
        if (args.length < 1) {
            System.out.println("Error! No arguments provided!");
        } else if (args.length == 2) {
            input = args[0];
            prefix = Integer.parseInt(args[1]);
            System.out.println("Doing the right thing!");
        } else if (args.length == 1){
            input = args[0];
            prefix = 1;
        } else {
            System.out.print("Error! Too many arguments provided!");
        }
        
        BirthdayPlanner bp = new BirthdayPlanner(prefix);
        List<String> list = bp.generate(input);
        
        System.out.print(bp.toString(list));
    }
}
