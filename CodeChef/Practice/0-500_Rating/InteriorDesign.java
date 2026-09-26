import java.util.Scanner;

class InteriorDesign {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x1=sc.nextInt();
            int y1=sc.nextInt();
            int x2=sc.nextInt();
            int y2=sc.nextInt();

            int cost1=x1+y1;
            int cost2=x2+y2;

            System.out.println(Math.min(cost1,cost2));
        }

        sc.close();
    }
}

/*

Sample Input
4
10 20 9 25
10 20 9 20
10 20 20 10
100 43 85 61
Your Output
30
29
30
143
  */
