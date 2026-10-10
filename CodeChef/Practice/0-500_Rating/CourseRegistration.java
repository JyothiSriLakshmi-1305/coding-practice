
import java.util.Scanner;

class CourseRegistration {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            int m=sc.nextInt();
            int k=sc.nextInt();

            if(n<=m-k) {
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
2 50 27
5 40 38
100 100 0
Your Output
Yes
No
Yes
  */
