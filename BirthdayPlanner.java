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

    public List<String> generate(String input) {
        boolean mainLast = false;
        boolean eatingLast = false;
        boolean hasRestaurant = false;
        boolean mainDouble = false;
        boolean added = false;

        List<String> list = new ArrayList<String>();
        Random rand = new Random();

        if (rand.nextBoolean()) {
            list.add(cafes.getRandomItem(input.substring(0,1)));
            eatingLast = true;
        } else {
            list.add(mains.getRandomItem(input.substring(0,1)));
            mainLast = true;
        }

            // choose one part of the string randopmly as the key
            int prefixLength = Math.min(rand.nextInt(prefix) + 1, input.length())
            String s = input.substring(0,prefixLength);
            while() {
            boolean chooseMain = rand.nextBoolean();
            String activity = null;
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
            if (activity != null) {
                list.add(activity);
                break;
            }
        }
        return list;
    }

    public static void main(String[] args) throws IOException{
        String input = args[0];
        int prefix = Integer.parseInt(args[1]);

        BirthdayPlanner bp = new BirthdayPlanner(prefix);
        List<String> list = bp.generate(input);

        for (String item : list) {
            item = item.substring(0,1).toUpperCase() + item.substring(1);
            System.out.println(item);
        }
    }
}
