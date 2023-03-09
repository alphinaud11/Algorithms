public class Analysis1_1 {

    public static int[][] array;
    public static int count;

    public static void tileQuad(int rowStart, int rowEnd, int colStart, int colEnd) {
        int horizontalSize = (colEnd - colStart) + 1;
        if (horizontalSize % 3 == 0) { //tile slabs horizontally
            for (int i=colStart; i<=colEnd; i+=3) {
                for (int j=rowStart; j<=rowEnd; j++) {
                    array[j][i] = count;
                    array[j][i+1] = count;
                    array[j][i+2] = count;
                    count++;
                }
            }
        }
        else { //tile slabs vertically
            for (int i=rowStart; i<=rowEnd; i+=3) {
                for (int j=colStart; j<=colEnd; j++) {
                    array[i][j] = count;
                    array[i+1][j] = count;
                    array[i+2][j] = count;
                    count++;
                }
            }
        }
    }

    public static void solve(int rowStart, int rowEnd, int colStart, int colEnd) {
        int n = (rowEnd - rowStart) + 1;
        if (n % 3 == 0) //case 1
            tileQuad(rowStart, rowEnd, colStart, colEnd);
        else if (n % 3 == 1) { //case 2
            array[rowEnd][colStart] = 1;
            tileQuad(rowStart, rowEnd-1, colStart, colEnd-1);
            tileQuad(rowEnd, rowEnd, colStart + 1, colEnd);
            tileQuad(rowStart, rowEnd - 1, colEnd, colEnd);
        }
        else if ((n % 3 == 2) && (n % 2 == 1)) { //case 3
            int mid = n / 2;
            array[mid][mid] = 1;
            tileQuad(0, mid-1, 0, mid); //top-left quad
            tileQuad(0, mid, mid+1, n-1); //top-right quad
            tileQuad(mid, n-1, 0, mid-1); //bottom-left quad
            tileQuad(mid+1, n-1, mid, n-1); //bottom-right quad
        }
        else { //case 4
            solve(2, n-3, 2, n-3); //middle quad
            tileQuad(0, n-3, 0, 1); //left quad
            tileQuad(0, 1, 2, n-1); //top quad
            tileQuad(2, n-1, n-2, n-1); //right quad
            tileQuad(n-2, n-1, 0, n-3); //bottom quad
        }
    }

    public static void main(String[] args) {
        int n = 3;
        count = 2;
        array = new int[n][n];
        solve(0, n-1, 0, n-1);
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++)
                System.out.print(array[i][j] + " \t");
            System.out.print("\n");
        }
    }

}
