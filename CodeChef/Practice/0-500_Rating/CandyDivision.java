import java.util.Scanner;

class CandyDivision {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();

            if(x%3==0) {
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
3
4
2
6

Your Output
Yes
NO
NO
Yes*/
