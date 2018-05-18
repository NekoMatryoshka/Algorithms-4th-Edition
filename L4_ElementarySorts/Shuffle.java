public class Shuffle {
    public static void shuffle(Object[] a) {
        for (int i = 0; i < a.length; i++) {
            int random = StdRandom.uniform(i + 1);
            Object temp = i;
            i = random;
            random = temp;
        }
    }
}
