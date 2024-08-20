import java.util.*;

class Edge implements Comparable<Edge>{
    int num;
    int cost;
    
    public Edge(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);   
    }
}

class Solution {
    
    static int[] staticGates;
    static int[] staticSummits;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Set<Integer> summitSet = new HashSet<>();
        for(int summit : summits) {
            summitSet.add(summit);
        }
        
        // 양방향 그래프 생성
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<Edge>());
        }
        for(int[] path : paths) {
            graph.get(path[0]).add(new Edge(path[1], path[2]));
            graph.get(path[1]).add(new Edge(path[0], path[2]));
        }
        
        // 다익스트라 알고리즘 수행
        int[] intensity = new int[n + 1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        intensity[0] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        
        // pq로 모든 노드들을 도는데 각 노드들은 maxCost라고 
        // 본인이 거쳐온 경로 상의 소요 시간 중에서 가장 큰 값을 끌고 이동한다.
        // 산봉우리에 도착 시, 본인이 갖고 있는 maxCost과 비교하여 더 작은 값을 갱신해준다. 
        
        // 왜 pq에 한 방에 gates를 다 넣는가?
        // 탐색 조건으로 새로 계산한 값이 본래보다 작아야 하는데, 이 계산 자체를
        // 출입구 후보에선 진행하지 않게 하기 위해 0으로 설정해준다. 
        for(int gate : gates) {
            pq.offer(new Edge(gate, 0));
            intensity[gate] = 0;
        }
        
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();
            
            // 현재 노드로 가기 위해 소요되는 비용이 현재 위치까지의 이미 적힌
            // cost보다 크다면 계산을 진행할 필요가 없다. 따라서 continue 처리
            if(cur.cost > intensity[cur.num]) continue;
            
            if(summitSet.contains(cur.num)) continue;
            
            for(Edge next : graph.get(cur.num)) {
                // 현재 노드의 비용과 그 다음 이동 노드까지의 소요 비용을 비교하여
                // 최대 비용을 갱신한다.
                int maxIntensity = Math.max(intensity[cur.num], next.cost);
                if(intensity[next.num] > maxIntensity) {
                    intensity[next.num] = maxIntensity;
                    pq.offer(new Edge(next.num, maxIntensity));
                }
            }
        }
            
        // 최소 intensity를 가지는 산봉우리 찾기
        int minSummit = -1;
        int minIntensity = Integer.MAX_VALUE;

        for(int summit : summits) {
            if(intensity[summit] < minIntensity){
                minIntensity = intensity[summit];
                minSummit = summit;
            } else if(intensity[summit] == minIntensity && summit < minSummit) {
                minSummit = summit;
            }

        }
        return new int[]{minSummit, minIntensity};
    }
}