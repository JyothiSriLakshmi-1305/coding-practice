import java.util.Scanner;
import java.util.Stack;

class NextSmallestElement {
    public static int[] nextSmallestElement(int[] arr) {
        int n=arr.length;
        int[] result=new int[n];
        Stack<Integer> stack=new Stack<>();

        for(int i=n-1;i>=0;i--) {
            while(!stack.isEmpty()&&arr[i]<=arr[stack.peek()]) {
                stack.pop();
            }

            if(!stack.isEmpty()) {
                result[i]=arr[stack.peek()];
            } else {
                result[i]=-1;
            }

            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        int[] arr=new int[n];

        for(int i=0;i<n;i++) {
            arr[i]=scanner.nextInt();
        }

        int[] result=nextSmallestElement(arr);

        for(int e:result) {
            System.out.print(e+" ");
        }

        scanner.close();
    }
}

/*
Input
Output
5
1 3 5 1 3
-1 1 1 -1 -1 
  */
