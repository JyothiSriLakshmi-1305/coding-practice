import java.io.*;
import java.util.*;

class StoneGame {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine().trim());
        StringBuilder sb=new StringBuilder();

        while(t-->0) {
            int n=Integer.parseInt(br.readLine().trim());
            StringTokenizer st=new StringTokenizer(br.readLine());
            Deque<Integer> dq=new ArrayDeque<>();

            for(int i=0;i<n;i++) {
                dq.offer(Integer.parseInt(st.nextToken()));
            }

            int lastPlayer=-1;
            boolean isAmanTurn=true;

            while(dq.size()>1) {
                if(isAmanTurn) {
                    if(dq.size()>1) {
                        int stone=dq.poll();
                        dq.offer(stone);
                    }

                    if(dq.size()>1) {
                        dq.poll();
                        lastPlayer=1;
                    }
                } else {
                    if(dq.size()>1) {
                        int stone=dq.poll();
                        dq.offer(stone);
                    }

                    if(dq.size()>1) {
                        int stone=dq.poll();
                        dq.offer(stone);
                    }

                    if(dq.size()>1) {
                        dq.poll();
                        lastPlayer=0;
                    }
                }

                isAmanTurn=!isAmanTurn;
            }

            int lastStone=dq.peek();
            sb.append(lastPlayer).append(" ").append(lastStone).append("\n");
        }

        System.out.print(sb.toString());
    }
}

/*
Sample Input
3
3
-5 0 5
4 
-1 -3 2 4
6
-100000 0 0 100000 -1000000 1000000
Your Output
0 -5
1 2
1 0
  */
