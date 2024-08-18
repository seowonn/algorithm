import java.util.*;

class Task implements Comparable<Task>{
    int startTime;
    int duration;
    
    public Task(int startTime, int duration) {
        this.startTime = startTime;
        this.duration = duration;
    }
    
    @Override
    public int compareTo(Task other) {
        if(this.startTime == other.startTime) {
            return Integer.compare(this.duration, other.duration);
        }
        return Integer.compare(this.startTime, other.startTime);
    }
}

class Solution {
    public int solution(int[][] jobs) {
        // 우선 순위 큐에 작업 다 삽입
        PriorityQueue<Task> pq = new PriorityQueue<>();
        for(int[] job : jobs) {
            pq.offer(new Task(job[0], job[1]));
        }
        
        // 최소 소요 시간 계산
        int curTime = 0;
        int costTime = 0;
        while(!pq.isEmpty()) {
            Task task = pq.poll();
            if(curTime <= task.startTime) {
                // 작업 진행에 따른 소요 시간 추가
                costTime += task.duration;
                // 현재 시간을 작업 끝 시간으로 맞춤
                curTime = task.startTime + task.duration;
            } else {
                // 현재 작업 끝 시간으로 startTime 조정. 대기 시간에 추가
                costTime += curTime - task.startTime;
                pq.offer(new Task(curTime, task.duration));
            }
        }
        return costTime / jobs.length;
    }
}