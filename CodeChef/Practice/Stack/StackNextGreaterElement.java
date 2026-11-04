import java.util.Scanner;
import java.util.Stack;

class StackNextGreaterElement {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int[] arr=new int[n];

        for(int i=0;i<n;i++) {
            arr[i]=sc.nextInt();
        }

        Stack<Integer> stack=new Stack<>();
        int[] nge=new int[n];

        for(int i=n-1;i>=0;i--) {
            while(!stack.isEmpty()&&stack.peek()<=arr[i]) {
                stack.pop();
            }

            nge[i]=stack.isEmpty()?-1:stack.peek();

            stack.push(arr[i]);
        }

        for(int i=0;i<n;i++) {
            System.out.print(nge[i]+" ");
        }

        sc.close();
    }
}
