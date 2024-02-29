import java.io.IOException;

class RestaurantStore extends ActivityStore{
    RestaurantStore() throws IOException {
        super("restaurants.txt");
    }
    RestaurantStore(int prefix) throws IOException {
        super("restaurants.txt", prefix);
    }
}
