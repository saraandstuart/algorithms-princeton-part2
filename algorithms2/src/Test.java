/**
 * For small tests
 * 
 * @author Stuart Shannon
 */
public class Test {
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        
        int sum = 0;
        for (int i = 1; i <= N*N; i++)
            for (int j = i; j <= i; j++)
                sum++;
        
        System.out.println(sum);

    }
}
