import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();

        Stack<Integer> dayNeededStack = new Stack<>();
        int[] dayNeeded = new int[progresses.length];

        for (int i = 0; i < progresses.length; i++) {
            int leftPercentage = 100 - progresses[i];
            int day = (leftPercentage % speeds[i] != 0) ? leftPercentage / speeds[i] + 1 : leftPercentage / speeds[i];
            dayNeeded[i] = day;
        }

        int mainDeploy = dayNeeded[0];
        dayNeededStack.push(mainDeploy);

        for (int i = 1; i < dayNeeded.length; i++) {
            if(dayNeeded[i] > mainDeploy){
                list.add(dayNeededStack.size());
                dayNeededStack.clear();
                mainDeploy = dayNeeded[i];
            }
            dayNeededStack.push(dayNeeded[i]);
        }

        list.add(dayNeededStack.size());

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}