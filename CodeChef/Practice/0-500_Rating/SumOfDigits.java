
import java.util.Scanner;

class SumOfDigits {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            int sum=0;

            while(n!=0) {
                int digit=n%10;
                sum+=digit;
                n=n/10;
            }

            System.out.println(sum);
        }

        sc.close();
    }
}
/*
Sample Input
3 
12345
31203
2123
Your Output
15
9
8*/
