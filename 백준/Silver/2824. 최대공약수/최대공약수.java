import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        BigInteger A = BigInteger.ONE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A = A.multiply(new BigInteger(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        BigInteger B = BigInteger.ONE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B = B.multiply(new BigInteger(st.nextToken()));
        }

        BigInteger gcd = A.gcd(B);
        String answer = gcd.toString();

        if(answer.length() > 9){
            System.out.println(answer.substring(answer.length() - 9));
        } else {
            System.out.println(answer);
        }
    }

}