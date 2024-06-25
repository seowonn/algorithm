import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {

            int convenienceStoreCnt = Integer.parseInt(br.readLine());
            StringTokenizer st;

            ArrayList<int[]> list = new ArrayList<>();
            for (int j = 0; j < convenienceStoreCnt + 2; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int[] point = new int[]{x, y};

                list.add(point);
            }

            // 인접 연결 리스트 생성
            Map<int[], ArrayList<int[]>> map = new HashMap<>();
            for (int j = 0; j < convenienceStoreCnt + 2; j++) {
                map.put(list.get(j), new ArrayList<>());
            }
            
            for (int j = 0; j < convenienceStoreCnt + 1; j++) {
                for (int k = j + 1; k < convenienceStoreCnt + 2; k++) {
                    int[] point1 = list.get(j);
                    int[] point2 = list.get(k);

                    if(Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]) <= 1000) {
                        map.get(point1).add(point2);
                        map.get(point2).add(point1);
                    }
                }
            }

            boolean ifPossible = checkIfPossible(map, list.get(0), list.get(convenienceStoreCnt + 1));
            System.out.println(ifPossible ? "happy" : "sad");
        }
    }

    public static boolean checkIfPossible(Map<int[], ArrayList<int[]>> map, int[] start, int[] end) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);

        Map<int[], Boolean> visited = new HashMap<>();
        for(int[] key : map.keySet()){
            visited.putIfAbsent(key, false);
        }
        visited.put(start, true);

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            if(poll[0] == end[0] && poll[1] == end[1]) {
                return true;
            }

            ArrayList<int[]> adjacent = map.get(poll);
            for (int[] points : adjacent) {
                if (!visited.get(points)) {
                    visited.put(points, true);
                    queue.add(points);
                }
            }
        }

        return false;
    }

}
