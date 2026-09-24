import java.util.Scanner;

class RollerCoaster {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();

            if(x>=y) {
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
15 20
50 48
32 32
38 39
Your Output
No
Yes
Yes
No
  */
