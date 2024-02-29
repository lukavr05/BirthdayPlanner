class BirthdayPlannerTest {
    public static void main(String[] args) {
        // QUESTION 1 TESTS
        ActivityStore as = new ActivityStore();
        as.add("a","apple pie");
        // check add function input
        assert as.getRandomItem("a").equals("apple pie");
        as.add("a","amalgum");
        // check that it is one of two inputs
        assert as.getRandomItem("a").equals("apple pie") || as.getRandomItem("a").equals("amalgum");

        as.add("b","brain");
        as.add("c","crematorium");

        // check that c has been added
        assert as.getRandomItem("c").equals("crematorium");
        // check for erroneous input
        assert as.getRandomItem("d").equals(null);

        // QUESTION 2 TESTS
    }
}
