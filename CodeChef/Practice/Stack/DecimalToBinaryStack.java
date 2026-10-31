import java.util.Scanner;

class DecimalToBinaryStack {
    static final int MAX_SIZE=101;
    static int[] a=new int[MAX_SIZE];
    static int top=-1;

    static void push(int ele) {
        if(top<MAX_SIZE-1) {
            a[++top]=ele;
        }
    }

    static int pop() {
        if(top>=0) {
            return a[top--];
        }
        return -1;
    }

    static boolean isEmpty() {
        return top==-1;
    }

    static int size() {
        return top+1;
    }

    static void decimalToBinary(int decimal) {
        if(decimal==0) {
            System.out.println(0);
            return;
        }

        while(decimal>0) {
            int remainder=decimal%2;
            push(remainder);
            decimal/=2;
        }

        while(!isEmpty()) {
            System.out.print(pop());
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int decimal=sc.nextInt();
            decimalToBinary(decimal);
        }

        sc.close();
    }
}
