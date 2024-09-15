class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int p1 = 0, p2 = 0;
        
        for(int i = 0; i < goal.length; i++) {
            
            String target = goal[i];
            
            if(p1 < cards1.length && target.equals(cards1[p1])) {
                p1++;
            } else if(p2 < cards2.length && target.equals(cards2[p2])) {
                p2++;
            } else {
                return "No";
            }
            
        }
        return "Yes";
    }
}