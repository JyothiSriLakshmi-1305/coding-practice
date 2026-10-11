
import java.util.Scanner;

class Insurance {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();

            if(y<=x) {
                System.out.println(y);
            } else {
                System.out.println(x);
            }
        }

        sc.close();
    }
}

/*
Sample Input
4
5 3
5 8
4 4
15 12
Your Output
3
5
4
12
  */
