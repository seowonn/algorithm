import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static String changedName = "";
    public static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String hanSuName = br.readLine();

        if(!isPalindrome(hanSuName)) {
            System.out.println("I'm Sorry Hansoo");
        } else {
            System.out.println(changedName);
        }
    }

    private static boolean isPalindrome(String name) {

        for (char c : name.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        boolean isEvenLength = name.length() % 2 == 0;
        boolean left = false;
        for (Map.Entry<Character, Integer> set : map.entrySet()) {
            if(set.getValue() % 2 == 1 && isEvenLength) {
                return false;
            } else if(set.getValue() % 2 == 1 && !isEvenLength) {
                if(left) {
                    return false;
                }
                left = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        Character center = null;
        for (Map.Entry<Character, Integer> set : map.entrySet()) {
            if(set.getValue() / 2 > 0) {
                sb.append(String.valueOf(set.getKey()).repeat(Math.max(0, set.getValue() / 2)));
            }
            if(set.getValue() % 2 == 1){
                center = set.getKey();
            }
        }

        char[] charArray = sb.toString().toCharArray();
        Arrays.sort(charArray);
        String original = new String(charArray);
        String reverse = new StringBuilder(new String(charArray)).reverse().toString();
        changedName = center == null ? original + reverse : original + center + reverse;
        return true;
    }
}