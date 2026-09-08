import java.util.Scanner;

class DecimalToBinary {
    static final int MAX_SIZE=101;
    static int[] a=new int[MAX_SIZE];
    static int top=-1;

    static void push(int ele) {
        if(top<MAX_SIZE-1) {
            a[++top]=ele;
        } else {
            System.out.println("Stack is full. Cannot push: "+ele);
        }
    }

    static int pop() {
        if(top>=0) {
            int ele=a[top];
            top--;
            return ele;
        } else {
            System.out.println("Stack is empty. Cannot pop.");
            return -1;
        }
    }

    static boolean isEmpty() {
        return top==-1;
    }

    static int size() {
        return top+1;
    }

    static void decimalToBinary(int decimal) {
        while(decimal>0) {
            int remainder=decimal%2;
            push(remainder);
            decimal/=2;
        }

        if(isEmpty()) {
            System.out.println("0");
            return;
        }

        while(!isEmpty()) {
            System.out.print(pop());
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();

        while(t-->0) {
            int decimal=scanner.nextInt();
            decimalToBinary(decimal);
        }

        scanner.close();
    }
}
/*
Input
Output
3
10
15
18
1010
1111
10010
*/
