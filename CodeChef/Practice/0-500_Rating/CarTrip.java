import java.util.Scanner;

class CarTrip {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();

            System.out.println((x<=300)?3000:x*10);
        }

        sc.close();
    }
}

/*
Sample Input
5
800
3
299
301
300
Your Output
8000
3000
3000
3010
3000

  */
