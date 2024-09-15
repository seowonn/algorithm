class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int p1 = 0, p2 = 0;
        int targetIdx = 0;
        
        while(targetIdx < goal.length) {
            boolean atLeastmatched = false;
            while(p1 < cards1.length && goal[targetIdx].equals(cards1[p1])) {
                targetIdx++;
                p1++;
                atLeastmatched = true;
            }
            while(p2 < cards2.length && targetIdx < goal.length && goal[targetIdx].equals(cards2[p2])) {
                targetIdx++;
                p2++;
                atLeastmatched = true;
            }
            if(!atLeastmatched) {
                return "No";
            }
        }
        return "Yes";
    }
}