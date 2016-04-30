/**
 * For small tests
 * 
 * @author Stuart Shannon
 */
public class Test 
{
    public static void main(String[] args) 
    {
        test();
    }

    private static void test()
    {
        // col_0,row_0 ; col_0,row_1 ; col_0,row_2
        // col_1,row_0 ; col_1,row_1 ; col_1,row_2
        // col_2,row_0 ; col_2,row_1 ; col_2,row_2
        
        int[][] color = new int[][]{
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8}
        };

        int[] seam = {1, 1, 2};

        System.out.print("before[col][row]: \n");
        print2DArrayContents(color);
        System.out.println();
        
        color = transposeMatrix(color);
        System.out.print("transposed[row][col]: \n");
        print2DArrayContents(color);
        System.out.println();

        for (int row = 0; row < seam.length; row++)
        {
            int col = seam[row];
            color[row] = removeElementByIndex(color[row], col);
        }
        
        System.out.print("after[row][col]: \n");
        print2DArrayContents(color);
        
        color = transposeMatrix(color);
        System.out.print("transposed[col][row]: \n");
        print2DArrayContents(color);
        System.out.println();
    }

    private static int[] removeElementByIndex(int[] input, int index)
    {
        int[] result = new int[input.length - 1];

        System.arraycopy(
                input, 
                0, 
                result, 
                0, 
                index);
        
        System.arraycopy(
                input,
                index + 1,
                result,
                index,
                input.length - index - 1);

        return result;
    }

    private static void printArrayContents(int[] a)
    {
        String sep = "";
        for (int i = 0; i < a.length ; i++)
        {
            System.out.print(sep + a[i]);
            sep = ", ";
        }
    }

    private static void print2DArrayContents(int[][] a)
    {
        for (int i = 0; i < a.length; i++)
        {
            printArrayContents(a[i]);
            System.out.println();
        }
    }
    
    public static int[][] transposeMatrix(int [][] input)
    {
        int width = input[0].length;
        int height = input.length;

        int[][] transposed = new int[width][height];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                transposed[j][i] = input[i][j];
            }
        }

        return transposed;
    }

}
