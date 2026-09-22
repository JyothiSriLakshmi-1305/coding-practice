import java.util.*;

class DequeReduction {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            Deque<Integer> dq=new ArrayDeque<>();

            for(int i=0;i<n;++i) {
                dq.addLast(sc.nextInt());
            }

            int ans=0;

            while(dq.size()>1) {
                int x=dq.pollFirst();
                int y=dq.pollLast();

                if(x==y) {
                    continue;
                }

                ++ans;

                if(x<y) {
                    dq.addLast(y-x);
                } else {
                    dq.addFirst(x-y);
                }
            }

            System.out.println(ans);
        }

        sc.close();
    }
}
/*
Sample Input
3
4
3 7 6 4
5
1 4 5 4 1
5
1 2 3 4 5
Your Output
2
0
4
  */
