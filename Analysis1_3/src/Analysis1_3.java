public class Analysis1_3 {

    public static String[][] triangle;

    /*
    Any n that is divisible by 3 will not provide a valid solutions since the number of the mini triangles will not be divisible by 3.
    Otherwise, any other n will provide a solution.
    The mini triangles are represented in the 2D array using strings, each 3 triangles represent a trapezoid as follows :-
    "LBD": A trapezoid with its long base down.
    "LBU": A trapezoid with its long base up.
    "LBL": A trapezoid with its long base on the left.
    "LBR": A trapezoid with its long base on the right.
     */

    public static void main(String[] args)
    {
        int n = 8;
        triangle = new String[n][(2 * n) - 1];
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < (2 * n) - 1; j++)
            {
                triangle[i][j] = "---";
            }
        }
        triangle[0][((2 * n) - 1) / 2] = "-X-";
        solve(n - 1, 0, (2 * n) - 2);
        for(String[] arr : triangle)
        {
            toString(arr);
        }
    }

    public static void solve(int currRow, int colStart, int colEnd)
    {
        if(currRow == 1)
        {
            triangle[currRow][colStart] = "LBD";
            triangle[currRow][colStart + 1] = "LBD";
            triangle[currRow][colEnd] = "LBD";
        }
        else
        {
            if(((2 * currRow) + 1) % 3 == 0)
            {
                boolean down = true;
                for(int curr = colStart; curr <= colEnd; curr += 3)
                {
                    if(down)
                    {
                        triangle[currRow][curr] = "LBD";
                        triangle[currRow][curr + 1] = "LBD";
                        triangle[currRow][curr + 2] = "LBD";
                        down = false;
                    }
                    else
                    {
                        triangle[currRow][curr] = "LBU";
                        triangle[currRow][curr + 1] = "LBU";
                        triangle[currRow][curr + 2] = "LBU";
                        down = true;
                    }
                }
                solve(currRow - 1, colStart + 1, colEnd - 1);
            }
            else
            {
                if(((2 * currRow) + 1) % 3 == 1)
                {
                    triangle[currRow][colStart] = "LBL";
                    triangle[currRow][colStart + 1] = "LBL";
                    triangle[currRow][colEnd] = "LBR";
                    triangle[currRow][colEnd - 1] = "LBR";
                    boolean down = true;
                    for(int curr = colStart + 2; curr <= colEnd - 2 ; curr += 3)
                    {
                        if(down)
                        {
                            triangle[currRow][curr] = "LBD";
                            triangle[currRow][curr + 1] = "LBD";
                            triangle[currRow][curr + 2] = "LBD";
                            down = false;
                        }
                        else
                        {
                            triangle[currRow][curr] = "LBU";
                            triangle[currRow][curr + 1] = "LBU";
                            triangle[currRow][curr + 2] = "LBU";
                            down = true;
                        }
                    }
                    solve(currRow - 1, colStart + 1, colEnd - 1);
                }
                else
                {
                    if(((2 * currRow) + 1) % 3 == 2)
                    {
                        triangle[currRow][colStart] = "LBL";
                        triangle[currRow][colEnd] = "LBR";
                        boolean down = false;
                        for(int curr = colStart + 1; curr <= colEnd - 1; curr += 3)
                        {
                            if(down)
                            {
                                triangle[currRow][curr] = "LBD";
                                triangle[currRow][curr + 1] = "LBD";
                                triangle[currRow][curr + 2] = "LBD";
                                down = false;
                            }
                            else
                            {
                                triangle[currRow][curr] = "LBU";
                                triangle[currRow][curr + 1] = "LBU";
                                triangle[currRow][curr + 2] = "LBU";
                                down = true;
                            }
                        }
                        solve(currRow - 1, colStart + 1, colEnd - 1);
                    }
                }
            }
        }
    }

    public static void toString(String[] arr)
    {
        String result = "";
        for(String s: arr)
        {
            result += s + " ";
        }
        System.out.println(result);
    }
}
