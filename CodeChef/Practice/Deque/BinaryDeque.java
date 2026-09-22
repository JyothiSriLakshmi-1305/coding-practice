import java.util.*;

class BinaryDeque {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            String s=sc.next();

            Deque<Character> dq=new ArrayDeque<>();
            int L=0,R=n-1;

            while(L<=R) {
                if(s.charAt(L)=='0') {
                    dq.addFirst('0');
                } else {
                    dq.addLast('1');
                }

                if(L<R) {
                    if(s.charAt(R)=='1') {
                        dq.addFirst('1');
                    } else {
                        dq.addLast('0');
                    }
                }

                ++L;
                --R;
            }

            StringBuilder result=new StringBuilder();

            for(char c:dq) {
                result.append(c);
            }

            System.out.println(result.toString());
        }

        sc.close();
    }
}

/*
10
0100
101101
0011011000
  */
