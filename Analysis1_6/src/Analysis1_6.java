import java.util.ArrayList;

public class Analysis1_6 {

    public static ArrayList<Integer> weights;

    public static void findWeights(int W, boolean bool) { // if bool is true then assume (a), if false assume (b)
        int base = bool ? 2 : 3;
        int exponent = 0;
        int sum = 0;
        while (W > sum) {
            weights.add((int) Math.pow(base, exponent));
            sum += Math.pow(base, exponent++);
        }
        System.out.println("n = " + exponent);
        System.out.println("Weights = " + weights);
    }

    public static void main(String[] args) {
        int W = 4;
        weights = new ArrayList<>();
        findWeights(W, true);
    }
}
