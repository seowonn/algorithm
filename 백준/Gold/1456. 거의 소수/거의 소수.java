import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        
        // 에라토스테네스의 체 생성
        boolean[] isPrime = new boolean[(int)Math.sqrt(b) + 1];

        for (int i = 2; i < isPrime.length; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i < isPrime.length; i++) {
            if(!isPrime[i]) continue;
            for (int j = i * 2; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }
        
        int answer = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if(isPrime[i]) {
                long temp = i;
                while (temp <= (double) b / i){
                    if(temp >= (double) a / i) {
                        answer++;
                    }
                    temp *= i;
                }
            }
        }
        System.out.println(answer);
    }
}