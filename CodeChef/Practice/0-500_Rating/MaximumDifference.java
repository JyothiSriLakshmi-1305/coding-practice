import java.util.Scanner;

class MaximumDifference {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            int m=sc.nextInt();

            System.out.println(Math.max(0,n-m));
        }

        sc.close();
    }
}

/*
Sample Input
4
5 3
5 7
4 1
8 8
Your Output
2
0
3
0*/
