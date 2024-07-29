import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int plateCnt = Integer.parseInt(st.nextToken());
        int sushiCnt = Integer.parseInt(st.nextToken());
        int continuousCnt = Integer.parseInt(st.nextToken());
        int couponNum = Integer.parseInt(st.nextToken());

        int[] sushi = new int[plateCnt];
        for (int i = 0; i < plateCnt; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        // 슬라이딩 윈도우와 해시맵 사용
        Map<Integer, Integer> map = new HashMap<>();
        int maxKinds = 0;

        // 초기 윈도우 설정
        for (int i = 0; i < continuousCnt; i++) {
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
        }

        // 쿠폰 초밥 고려
        maxKinds = map.size();
        if (!map.containsKey(couponNum)) {
            maxKinds++;
        }

        // 슬라이딩 윈도우 시작
        for (int i = 1; i < plateCnt; i++) {
            int endIdx = (i + continuousCnt - 1) % plateCnt;
            map.put(sushi[endIdx], map.getOrDefault(sushi[endIdx], 0) + 1);

            // 시작 인덱스 초밥 제거
            int startIdx = i - 1;
            if (map.get(sushi[startIdx]) == 1) {
                map.remove(sushi[startIdx]);
            } else {
                map.put(sushi[startIdx], map.get(sushi[startIdx]) - 1);
            }

            // 현재 윈도우의 최대 종류 계산
            int currentKinds = map.size();
            if (!map.containsKey(couponNum)) {
                currentKinds++;
            }

            maxKinds = Math.max(maxKinds, currentKinds);
        }

        System.out.println(maxKinds);
    }
}
