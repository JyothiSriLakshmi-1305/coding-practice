import java.util.Scanner;

class YMinusN.java {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            int y=sc.nextInt();

            System.out.println(y-n);
        }

        sc.close();
    }
}
/*
Sample 1:
Input

4
1 2
3 7
9 11
2 10
Output:
1
4
2
8
*/
