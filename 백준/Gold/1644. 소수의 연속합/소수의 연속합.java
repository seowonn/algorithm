import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int maxCnt = 4_000_000;
        boolean[] isPrime = new boolean[maxCnt + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i <= Math.sqrt(4_000_000); i++) {
            if(isPrime[i]) {
                for (int j = i + i; j <= maxCnt; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int p1 = 1, p2 = 1;
        int sum = 0;
        int answer = 0;
        while(p1 < maxCnt && p2 < maxCnt) {
            if(sum < N){
                while (p2 < maxCnt && !isPrime[p2]){
                    p2++;
                }
                sum += p2++;
            } else if(sum > N){
                while (p1 < maxCnt && !isPrime[p1]){
                    p1++;
                }
                sum -= p1++;
            } else {
                answer++;
                while (p2 < maxCnt && !isPrime[p2]){
                    p2++;
                }
                sum += p2++;
            }
        }

        System.out.println(answer);
    }
}