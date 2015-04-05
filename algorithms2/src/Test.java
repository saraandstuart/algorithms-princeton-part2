
public class Test {
    public static void main(String[] args) {

        int N = Integer.parseInt(args[0]);
        
        int sum = 0;
        for (int i = 0; i < N*N; i++)
            for (int j = i; j < N; j++)
                sum++;
        
        System.out.println(sum);

    }
}
