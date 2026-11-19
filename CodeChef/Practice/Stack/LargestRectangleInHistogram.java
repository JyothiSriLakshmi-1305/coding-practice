import java.util.Scanner;
import java.util.Stack;

class LargestRectangleInHistogram {
    public static long largestRectangleArea(int n,int[] a) {
        Stack<Integer> stack=new Stack<>();
        long maxArea=0;
        int i=0;

        while(i<n) {
            if(stack.isEmpty()||a[i]>=a[stack.peek()]) {
                stack.push(i++);
            } else {
                int tp=stack.pop();
                long width=stack.isEmpty()?i:i-stack.peek()-1;
                maxArea=Math.max(maxArea,(long)a[tp]*width);
            }
        }

        while(!stack.isEmpty()) {
            int tp=stack.pop();
            long width=stack.isEmpty()?i:i-stack.peek()-1;
            maxArea=Math.max(maxArea,(long)a[tp]*width);
        }

        return maxArea;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int[] a=new int[n];

        for(int i=0;i<n;i++) {
            a[i]=sc.nextInt();
        }

        System.out.println(largestRectangleArea(n,a));

        sc.close();
    }
}
