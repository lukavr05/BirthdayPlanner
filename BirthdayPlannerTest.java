import java.io.IOException;

class BirthdayPlannerTest {
    public static void main(String[] args) throws IOException {
        // QUESTION 1 TESTS
        ActivityStore as0 = new ActivityStore();
        as0.add("a","apple pie");
        // check add function input
        String test1 = as0.getRandomItem("a");
        assert test1.equals("Apple pie");
        as0.add("a","amalgum");
        // check that it is one of two 
        String test2 = as0.getRandomItem("a");
        assert test2.equals("Apple pie") || test2.equals("Amalgum");

        as0.add("b","brain");
        as0.add("c","crematorium");

        // check that c has been added
        assert as0.getRandomItem("c").equals("Crematorium");
        // check for erroneous input
        assert as0.getRandomItem("d") == (null);

        // QUESTION 2 TESTS
        ActivityStore as1 = new ActivityStore("cafes.txt");

        // check that it is actually reading from the file
        String test3 = as1.getRandomItem("y");
        assert test3.equals("You and me") || test3.equals("Young's cafe");

        // test it handles a file not existing
        ActivityStore as2 = new ActivityStore("blah.txt");
    }
}
