class Solution {
    public String solution(String new_id) {
        
        // 1. 소문자 치환
        new_id = new_id.toLowerCase();
        
        // 2. 알파벳 소문자, 숫자, 빼기, 밑줄, 마침표 제외 다 제거 
        String[] nonPreferedSign = {};
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");
        
        // 3. 마침표 연속 2개 1개로 치환
        while(new_id.contains("..")){
            new_id = new_id.replace("..", ".");
        }
        
        // 4. 처음/끝 마침표 제거
        while(new_id.startsWith(".")){
            new_id = new_id.substring(1);
        }
        while(new_id.endsWith(".")){
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        
        // 5. 빈 문자열 체크
        if(new_id.isEmpty()){
            new_id = "a";
        }
        
        // 6. 길이 
        if(new_id.length() >= 16){
            new_id = new_id.substring(0, 15);
        }
        while(new_id.endsWith(".")){
            new_id = new_id.substring(0, new_id.length() - 1);
        }
        
        // 7. 길이 2이하
        Character last = new_id.charAt(new_id.length() - 1);
        while(new_id.length() < 3){
            new_id += String.valueOf(last);
        }
        
        return new_id;
    }
}