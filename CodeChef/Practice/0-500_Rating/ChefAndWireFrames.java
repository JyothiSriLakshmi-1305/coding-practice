import java.util.Scanner;

class ChefAndWireFrames {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            int m=sc.nextInt();
            int x=sc.nextInt();

            System.out.println(2*x*(n+m));
        }

        sc.close();
    }
}

/*
Sample Input
3
10 10 10
23 3 12
1000 1000 1000
Your Output
400
624
4000000
  */
