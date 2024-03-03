import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ActivityStore {
    // mapping each string key to an array containing all the activities
    private HashMap<String, ArrayList<String>> hash;

    ActivityStore() {
        hash = new HashMap<String, ArrayList<String>>();
    }

    ActivityStore(String filename) throws IOException{
        hash = new HashMap<String, ArrayList<String>>();
        BufferedReader br;
        try {
        // create new buffered reader
            br = new BufferedReader(new FileReader(filename));
            // activity string is contained in the line of the txt file
            String activity = br.readLine();
            // while there are still files to read
            while (activity != null) {
                // extract first char for key
                String key = activity.substring(0,1);
                if (filename.equals("cafes.txt") || filename.equals("restaurants.txt")) {
                    String type = " (" + filename.substring(0,filename.length() - 5) +")";
                    this.add(key, activity + type);
                } else {
                    this.add(key, activity);
                }
                // next activity
                activity = br.readLine();
            }
            // close buffered reader
            br.close();
        } catch (IOException e) {
            System.err.print("Error! File does not exist!");
        }
    }

    ActivityStore(String filename, int prefix) throws IOException{
        hash = new HashMap<String, ArrayList<String>>();
        BufferedReader br;
        try {
        // create new buffered reader
            br = new BufferedReader(new FileReader(filename));
            // activity string is contained in the line of the txt file
            String activity = br.readLine();
            // while there are still lines to read
            while (activity != null) {
                prefix = Math.min(activity.length(), prefix); 
                for (int x = 0 ; x < prefix ; x++) {
                    String key = activity.substring(0,x + 1);
                    if (filename.equals("cafes.txt") || filename.equals("restaurants.txt")) {
                        String type = " (" + filename.substring(0,filename.length() - 5) +")";
                        this.add(key, activity + type);
                    } else {
                        this.add(key, activity);
                    }
                }
                // next activity
                activity = br.readLine();
            }
            // close buffered reader
            br.close();
        } catch (IOException e) {
            System.err.print("Error! File does not exist!");
        }
    }

    public HashMap<String, ArrayList<String>> getHash() {
        return this.hash;
    }

    public void add(String key, String item){
        // ensure key is uppercase
        key = key.toUpperCase();

        // create new array if the key does not exist
        // note hash.get(key) references the array at the loaction of the key
        if (!hash.containsKey(key)) {
            hash.put(key, new ArrayList<String>());
            hash.get(key).add(item);
        } else {
            // add item to the array
            hash.get(key).add(item);
        }
    }

    public String getRandomItem(String key) {
        // ensure key is upper case
        key = key.toUpperCase();
        if (!hash.containsKey(key)){
            return null;
        }
        Random rand = new Random();
        if (hash.get(key).size() == 0) {
            return null;
        }

        String a = hash.get(key).get(rand.nextInt(hash.get(key).size()));
        String caps = a.substring(0,key.length());
        caps = caps.toUpperCase();

        return caps + a.substring(key.length());
    }
}
