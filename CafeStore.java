import java.io.IOException;

class CafeStore extends ActivityStore{
    CafeStore() throws IOException {
        super("cafes.txt");
    }
    CafeStore(int prefix) throws IOException {
        super("cafes.txt", prefix);
    }

    // use override method maybe
}
