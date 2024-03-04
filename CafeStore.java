import java.io.IOException;
import java.util.ArrayList;

class CafeStore extends ActivityStore{
    CafeStore() throws IOException {
        super("cafes.txt");
    }
    CafeStore(int prefix) throws IOException {
        super("cafes.txt", prefix);
    }

    @Override
    protected void add(String key, String item) {
        key = key.toUpperCase();

        // create new array if the key does not exist
        if (!hash.containsKey(key)) {
            hash.put(key, new ArrayList<String>());
            hash.get(key).add(item + " (cafe)");
        } else {
            // add item to the array
            hash.get(key).add(item + " (cafe)");
        }
    }
}
