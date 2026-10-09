
import java.util.Scanner;

class PendingAssignments {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();
            int z=sc.nextInt();

            int perDay=24*60;
            int required=x*y;
            int available=z*perDay;

            if(required<=available) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

        sc.close();
    }
}

/*
Sample Input
3
5 5 5
50 80 2
20 72 1
Your Output
Yes
No
Yes
  */
