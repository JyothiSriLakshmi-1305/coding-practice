import java.util.Scanner;

class ReachHome {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();

            if(y<=5*x) {
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
4
2 10
3 17
4 2
6 45
Your Output
Yes
No
Yes
No
*/
