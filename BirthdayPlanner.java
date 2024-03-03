import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

class BirthdayPlanner {
    private ActivityStore mains;
    private RestaurantStore restaurants;
    private CafeStore cafes;
    private int prefix;

    BirthdayPlanner(int prefix) throws IOException{
        mains = new ActivityStore("main-activities.txt", prefix);
        restaurants = new RestaurantStore(prefix);
        cafes = new CafeStore(prefix);
        this.prefix = prefix;
    }

    public String capitalisePrefix(String s, int prefix) {
        s = s.substring(0,prefix) + s.substring(prefix);
        return s;
    }

    public List<String> generate(String input) {
        boolean mainLast = false;
        boolean eatingLast = false;
        boolean hasRestaurant = false;
        boolean mainDouble = false;

        List<String> list = new ArrayList<String>();
        Random rand = new Random();

        // choose one part of the string randopmly as the key
            
        while (input.length() > 0) {
            boolean added = false;
            int prefixLength = Math.min(rand.nextInt(prefix) + 1, input.length());
            String activity = null;
            while (!added) {
                String s = input.substring(0,prefixLength);
                boolean chooseMain = rand.nextBoolean();
                // generating either cafe or main
                if (chooseMain || eatingLast) {
                    if (mainLast && !mainDouble) {
                        activity = mains.getRandomItem(s);
                        eatingLast = false;
                        mainLast = true;
                        mainDouble = true;
                    } else if (eatingLast) {
                        activity = mains.getRandomItem(s);
                        eatingLast = false;
                        mainLast = true;
                        mainDouble = false;
                    } else {
                        activity = cafes.getRandomItem(s);
                        eatingLast = true;
                        mainLast = false;
                        mainDouble = false;
                    }
                } else {
                    if (!hasRestaurant && rand.nextBoolean()) {
                        activity = restaurants.getRandomItem(s);
                        eatingLast = true;
                        mainLast = false;
                        hasRestaurant = true;
                    } else {
                        activity = cafes.getRandomItem(s);
                        eatingLast = true;
                        mainLast = false;
                    }
                }
                // if we dont have an item that matches then decrement the prefix and make sure to set added to false
                if (activity == null) {
                    prefixLength--;
                    added = false;
                } else {
                    list.add(capitalisePrefix(activity, prefixLength));
                    input = input.substring(prefixLength);
                    added = true;
                }
            }

        }
        return list;
    }

    public String toString(List<String> list) {
        String s = "";
        for (String item : list) {
            s += item + "\n";
        }
        return s;
    }

    public static void main(String[] args) throws IOException{
        String input = args[0];
        int prefix = Integer.parseInt(args[1]);

        BirthdayPlanner bp = new BirthdayPlanner(prefix);
        List<String> list = bp.generate(input);
        
        System.out.print(bp.toString(list));
    }
}
