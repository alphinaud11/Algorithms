import java.util.ArrayList;
import java.util.Arrays;

public class Analysis1_8 {

    public static void partition(int sum, ArrayList<Integer> values) {
        int numRows = sum + 1;
        int numCols = values.size() + 1;
        boolean[][] table = new boolean[numRows][numCols];
        ArrayList<Integer> subset = new ArrayList<>();
        int[] index = new int[numRows]; // array to save indices of values included in subset

        for (int j=0; j<numCols; j++) {
            table[0][j] = true;
        }

        for (int i=1; i<numRows; i++) {
            table[i][0] = false;
        }

        for (int j=1; j<numCols; j++) {
            for (int i=0; i<numRows; i++) {
                if (table[i][j-1]) { // if cell before it is True
                    table[i][j] = true;
                    if (((i + values.get(j-1)) < numRows)) {
                        table[(i + values.get(j-1))][j] = true; // we can have subset with value = current sum + value at index[j-1]
                        if (!table[(i + values.get(j-1))][j-1])
                            index[(i + values.get(j-1))] = j - 1;
                    }
                }
            }
        }

        // get the subset
        int i = numRows - 1;
        while (i > 0) {
            subset.add(values.get(index[i]));
            i -= values.get(index[i]);
        }

        // remove subset from values
        for (int v : subset) {
            values.remove(Integer.valueOf(v));
        }

        // print subset
        System.out.print(subset + "\n");
    }

    public static void main(String[] args) {
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(4, 5, 5, 5, 6, 7, 5, 5, 3));
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        System.out.println("Value : " + (sum/3));
        System.out.print("First subset => ");
        partition(sum/3, values);
        System.out.print("Second subset => ");
        partition(sum/3, values);
        System.out.print("Third subset => " + values);
    }
}
