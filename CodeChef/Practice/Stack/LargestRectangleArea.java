import java.util.Stack;

class LargestRectangleArea {
    public static long largestRectangleArea(int N,int[] A) {
        Stack<Integer> s=new Stack<>();
        long maxArea=0;
        int i=0;

        while(i<N) {
            if(s.isEmpty()||A[i]>=A[s.peek()]) {
                s.push(i++);
            } else {
                int tp=s.pop();
                long width=s.isEmpty()?i:i-s.peek()-1;
                maxArea=Math.max(maxArea,(long)A[tp]*width);
            }
        }

        while(!s.isEmpty()) {
            int tp=s.pop();
            long width=s.isEmpty()?i:i-s.peek()-1;
            maxArea=Math.max(maxArea,(long)A[tp]*width);
        }

        return maxArea;
    }
}

/*
3
4
2 6 8 4
4
5 10 5 2
2
5 5
Your Output
12
15
10
  */
