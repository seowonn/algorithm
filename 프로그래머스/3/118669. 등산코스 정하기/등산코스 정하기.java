import java.util.*;
import java.util.stream.*;

class Edge {
    int node;
    int weight;
    
    public Edge(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}

class Entry implements Comparable<Entry> {
    int intensity;
    int node;
    
    public Entry(int intensity, int node) {
        this.intensity = intensity;
        this.node = node;
    }
    
    @Override
    public int compareTo(Entry other) {
        // 결과 음수 : this가 우선. 즉 오름차순
        return this.intensity - other.intensity;
    }
}

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 입력값을 내가 쓰기 편한 구조로 바꾼다. => 무방향(양방향) 그래프로 바꾼다.
        Arrays.sort(summits);
        Set<Integer> summitSet = IntStream.of(summits).boxed().collect(Collectors.toSet());
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for(int[] path : paths) {
            graph.putIfAbsent(path[0], new LinkedList<>());
            graph.putIfAbsent(path[1], new LinkedList<>());
            
            graph.get(path[0]).add(new Edge(path[1], path[2]));
            graph.get(path[1]).add(new Edge(path[0], path[2]));
        }
        
        // 모든 출입구를 우선순위 큐에 삽입한다. 
        // 모든 출입구는 intenisty를 0으로 설정하고, 재방문을 방지하기 위해
        // visited의 gate 위치는 다 0으로 방문 처리한다.
        Queue<Entry> hq = new PriorityQueue<>();
        int[] visited = new int[n + 1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        for(int gate : gates) {
            hq.add(new Entry(0, gate));
            visited[gate] = 0;
        }
        
        // intensity를 기준으로 다익스트라를 진행한다.
        while(!hq.isEmpty()) {
            Entry entry = hq.poll();
            
            // entry의 노드 번호가 산봉우리 중 하나라면 탐색을 중단 (hq에 넣지 않음)한다.
            // 또한 현재 entry의 intensity가 방문 배열의 해당 위치에 저장된 값보다 크다면 굳이 탐색을 더하지 않는다.
            if(entry.intensity > visited[entry.node] || summitSet.contains(entry.node)){
                continue;
            }
            
            for(Edge edge : graph.get(entry.node)){
                int nextIntensity = Math.max(edge.weight, entry.intensity);
                // 현재 탐색 대상인 edge.node까지의 역대 최대 intensity 값과 현재 구한 intensity값을 비교한다. 
                if(nextIntensity < visited[edge.node]) {
                    // 현재 구한 intensity 값이 더 작다면 그 값으로 visited를 갱신해준다. 
                    visited[edge.node] = nextIntensity;
                    // Entry는 hq에 들어갈, 탐색 대상이 되는 노드 후보이다. 
                    // Entry는 역대 최대 intensity 값과 해당 노드로 구성된다. 
                    hq.add(new Entry(nextIntensity, edge.node));
                }
            }
        }
        
        // 다익스트라 완료 후 산봉우리들을 순회하며 정답을 찾는다.
        int[] minIntensity = {0, Integer.MAX_VALUE};
        for(int summit : summits) {
            if(minIntensity[1] > visited[summit]) {
                minIntensity[0] = summit;
                minIntensity[1] = visited[summit];
            }
        }
        
        return minIntensity;
    }
}