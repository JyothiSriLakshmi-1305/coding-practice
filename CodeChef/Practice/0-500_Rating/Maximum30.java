import java.util.Scanner;

class Maximum30 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();

            if(x>=30) {
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
6
30
60
14
29
31
42
Your Output
Yes
Yes
No
No
Yes
Yes*/
