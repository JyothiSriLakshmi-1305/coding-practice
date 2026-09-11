import java.util.Scanner;
import java.util.Stack;

class NextGreaterElement {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr=new int[n];

        for(int i=0;i<n;i++) {
            arr[i]=scanner.nextInt();
        }

        Stack<Integer> stack=new Stack<>();
        int[] nge=new int[n];

        for(int i=n-1;i>=0;i--) {
            while(!stack.isEmpty()&&stack.peek()<arr[i]) {
                stack.pop();
            }

            nge[i]=stack.isEmpty()?-1:stack.peek();
            stack.push(arr[i]);
        }

        for(int i=0;i<n;i++) {
            System.out.print(nge[i]+" ");
        }

        scanner.close();
    }
}

/*
Input
Output
4 
4 5 2 25
5 25 25 -1
  */
