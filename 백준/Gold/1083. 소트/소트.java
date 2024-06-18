import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int S = Integer.parseInt(br.readLine());

        int cnt = 0, maxIdx = 0, startIdx = 0;
        while (cnt < S && startIdx < N - 1) {
            maxIdx = startIdx;
            // 최대 S 범위 내로 순회를 하면서 가장 큰 값의 위치를 찾는다.
            for(int i = startIdx + 1; i < N && i - startIdx <= S - cnt; i++) {
                if(nums[i] > nums[maxIdx]) {
                    maxIdx = i;
                }
            }

            // 그 큰수를 맨 앞자리까지 인접애랑 교환하면서 앞으로 보낸다.
            while(cnt < S && maxIdx > startIdx){
                int tmp = nums[maxIdx];
                nums[maxIdx] = nums[maxIdx - 1];
                nums[maxIdx - 1] = tmp;
                cnt++;
                maxIdx--;
            }

            startIdx++;
        }

        for (int i = 0; i < N - 1; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println(nums[N - 1]);
    }

}
