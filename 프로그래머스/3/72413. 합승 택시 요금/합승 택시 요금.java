import java.util.*;

class Edge implements Comparable<Edge>{
    int next;
    int cost;
    
    public Edge(int next, int cost) {
        this.next = next;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.cost, other.cost);
    }
}

class Solution {
    
    static final int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        // 그래프 생성
        Map<Integer, ArrayList<Edge>> graph = new HashMap<>();
        for(int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for(int[] fare : fares){
            graph.get(fare[0]).add(new Edge(fare[1], fare[2]));
            graph.get(fare[1]).add(new Edge(fare[0], fare[2]));
        }
        
        int answer = Integer.MAX_VALUE;
        int[] distance = new int[n + 1];
        
        // 파트 1. 합승 마지막 지점을 1부터 n까지 1번씩 돌아가면서 구하기
        for(int point = 1; point <= n; point++) {
            int together = getMinCost(graph, distance, s, point);
            
            // 파트 2. 합승 지점을 시작점, 각각 a, b까지의 최소 비용을 구해서 더한다. 
            together += getMinCost(graph, distance, point, a);
            together += getMinCost(graph, distance, point, b);
            
            answer = Math.min(answer, together);
        }
        
        return answer;
    }
    
    private int getMinCost(Map<Integer, ArrayList<Edge>> graph, int[] distance, int start, int end) {
        Arrays.fill(distance, INF);
        distance[0] = 0;
        distance = dijkstra(graph, start, end, distance);
        return distance[end];
    }
    
    private int[] dijkstra(Map<Integer, ArrayList<Edge>> graph, int s, int end, int[] distance) {
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(s, 0));
        distance[s] = 0;
        
        while(!pq.isEmpty()) {
            Edge cur = pq.remove();
            
            if(cur.next == end) {
                return distance;
            }
            
            for(Edge node : graph.get(cur.next)) {
                if(distance[node.next] > cur.cost + node.cost) {
                    distance[node.next] = cur.cost + node.cost;
                    pq.offer(new Edge(node.next, distance[node.next]));
                }
            }
        }
        
        return distance;
    }
}