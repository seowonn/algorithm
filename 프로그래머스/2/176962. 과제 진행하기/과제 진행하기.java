import java.util.*;

class Task {
    String name;
    int remainingTime;
    
    Task(String name, int remainingTime) {
        this.name = name;
        this.remainingTime = remainingTime;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        // 1. 과제 목록을 시작 시각 기준으로 정렬
        Arrays.sort(plans, (a, b) -> timeToMinutes(a[1]) - timeToMinutes(b[1]));
        
        Stack<Task> pausedTasks = new Stack<>(); // 멈춘 과제들을 저장할 스택
        List<String> finishedTasks = new ArrayList<>(); // 완료된 과제 이름을 저장
        
        int currentTime = 0; // 현재 시간 (분 단위)
        
        for(String[] plan : plans) {
            String name = plan[0];
            int startTime = timeToMinutes(plan[1]);
            int playTime = Integer.parseInt(plan[2]);
            
            // 2. 새로운 과제를 시작할 시간이 되면 진행 중인 과제를 멈춤
            while(!pausedTasks.isEmpty() && currentTime < startTime) {
                Task task = pausedTasks.pop();
                if(currentTime + task.remainingTime <= startTime) {
                    // 과제를 다 끝낼 수 있으면 끝내기
                    currentTime += task.remainingTime;
                    finishedTasks.add(task.name);
                } else {
                    // 시간이 부족하여 과제를 다 못 끝내는 경우 남은 시간 업데이트 후 멈춤
                    task.remainingTime -= (startTime - currentTime);
                    pausedTasks.push(task);
                    currentTime = startTime;
                    break;
                }
            }
            
            // 3. 새로운 과제를 시작
            currentTime = startTime;
            pausedTasks.push(new Task(name, playTime));
        }
        
        // 4. 남은 과제를 모두 끝내기
        while(!pausedTasks.isEmpty()) {
            finishedTasks.add(pausedTasks.pop().name);
        }
        
        return finishedTasks.toArray(new String[0]);
    }
    
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}