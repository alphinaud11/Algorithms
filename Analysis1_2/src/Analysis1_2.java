import java.util.ArrayList;

public class Analysis1_2 {

    public static String[][] arr;
    public static ArrayList<String> colors;

    public static void fillColors() {
        colors = new ArrayList<>();
        colors.add("R");
        colors.add("G");
        colors.add("B");
    }

    public static void checkSurrounding(int x, int y) {
        if (x != 0) {
            colors.remove(arr[x-1][y]);
        }
        if (y != 0) {
            colors.remove(arr[x][y-1]);
        }
        if (x != arr.length-1) {
            colors.remove(arr[x+1][y]);
        }
        if (y != arr.length-1) {
            colors.remove(arr[x][y+1]);
        }
    }

    public static void tile(int x1, int y1, int x2,
               int y2, int x3, int y3)
    {
        checkSurrounding(x1, y1);
        checkSurrounding(x2, y2);
        checkSurrounding(x3, y3);
        arr[x1][y1] = colors.get(0);
        arr[x2][y2] = colors.get(0);
        arr[x3][y3] = colors.get(0);
        fillColors();
    }

    public static void solve(int n, int x, int y)
    {
        int row = 0; //row of used square
        int column = 0; //column of used square
        if (n == 2) { //base case
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[x + i][y + j].equals("x")) {
                        checkSurrounding(x+i, y+j);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[x + i][y + j].equals("x")) {
                        arr[x + i][y + j] = colors.get(0);
                    }
                }
            }
            fillColors();
            return;
        }

        // finding used square
        for (int i = x; i < x+n; i++)
        {
            for (int j = y; j <y+ n; j++)
            {
                if (!arr[i][j].equals("x")) {
                    row = i;
                    column = j;
                }
            }
        }
        // If used square is in first quad
        if (row < x + n / 2 && column < y + n / 2)
            tile(x + n / 2, y + (n / 2) - 1, x + n / 2,
                    y + n / 2, x + n / 2 - 1, y + n / 2);

            // If used square is in second quad
        else if (row >= x + n / 2 && column < y + n / 2)
            tile(x + (n / 2) - 1, y + (n / 2), x + (n / 2),
                    y + n / 2, x + (n / 2) - 1, y + (n / 2) - 1);

            // If used square is in third quad
        else if (row < x + n / 2 && column >= y + n / 2)
            tile(x + n / 2, y + (n / 2) - 1, x + n / 2,
                    y + n / 2, x + n / 2 - 1, y + n / 2 - 1);

            // If used square is in fourth quad
        else if (row >= x + n / 2 && column >= y + n / 2)
            tile(x + (n / 2) - 1, y + (n / 2), x + (n / 2),
                    y + (n / 2) - 1, x + (n / 2) - 1,
                    y + (n / 2) - 1);

        // dividing it into 4 quads
        solve(n / 2, x, y); //first quad
        solve(n / 2, x, y + n / 2); //second quad
        solve(n / 2, x + n / 2, y); //third quad
        solve(n / 2, x + n / 2, y + n / 2); //fourth quad
    }

    public static void main(String[] args) {
        int n = 2;
        int length = (int) Math.pow(2,n);
        arr = new String[length][length];
        fillColors();
        for (int i=0; i<length; i++) { //filling arr with default x
            for (int j = 0; j < length; j++)
                arr[i][j] = "x";
        }
        arr[0][0] = "X"; //missing square
        solve(length, 0, 0);
        for (int i=0; i<length; i++) {
            for (int j=0; j<length; j++)
                System.out.print(arr[i][j] + " \t");
            System.out.print("\n");
        }
    }

}
