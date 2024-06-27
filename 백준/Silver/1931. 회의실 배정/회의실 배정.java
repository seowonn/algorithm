import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Meeting implements Comparable<Meeting>{
    int startTime;
    int endTime;

    public Meeting(int startTime, int endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int compareTo(Meeting o) {
        if(o.endTime > this.endTime) {
            return -1;
        } else if(o.endTime == this.endTime) {
            if(o.startTime > this.startTime) {
                return -1;
            } else {
                return 1;
            }
        }
        return 1;
    }

}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int meetingCnt = Integer.parseInt(br.readLine());

        StringTokenizer st;
        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        for (int i = 0; i < meetingCnt; i++) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int currentTime = 0, answer = 0;
        while (!pq.isEmpty()) {
            Meeting meeting = pq.poll();
            if(currentTime <= meeting.startTime) {
                answer++;
                currentTime = meeting.endTime;
            }
        }

        System.out.println(answer);

    }

}
