import java.io.IOException;
import java.util.ArrayList;

class RestaurantStore extends ActivityStore{
    RestaurantStore() throws IOException {
        super("restaurants.txt");
    }
    RestaurantStore(int prefix) throws IOException {
        super("restaurants.txt", prefix);
    }

    @Override
    protected void add(String key, String item) {
        key = key.toUpperCase();

        // create new array if the key does not exist
        if (!hash.containsKey(key)) {
            hash.put(key, new ArrayList<String>());
            hash.get(key).add(item + " (restaurant)");
        } else {
            // add item to the array
            hash.get(key).add(item + " (restaurant)");
        }
    }
}
