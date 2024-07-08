import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());

        int n = 2;
        int answer = 0;
        ArrayList<String> result = new ArrayList<>();

        while(k != 1) {
            if (n >= 1000000) {
                result.add(k+"");
                answer++;
                break;
            }
            if (k % n == 0) {
                k /= n;
                answer++;
                result.add(n+"");
            }
            else {
                n++;
            }
        }

        System.out.println(answer);
        System.out.println(String.join(" ", result));
    }


}