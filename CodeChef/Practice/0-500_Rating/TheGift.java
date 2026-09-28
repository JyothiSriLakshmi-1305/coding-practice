import java.util.Scanner;

class TheGift {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int x=sc.nextInt();
        int n=sc.nextInt();
        int m=sc.nextInt();

        if(n<=x+m) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        sc.close();
    }
}

/*
Sample Input
5 10 15
Your Output
Yes
  */
