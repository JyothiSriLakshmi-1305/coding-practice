import java.util.Scanner;

class TimeComplexity {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int a=sc.nextInt();
            int b=sc.nextInt();

            if(a>b) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}

/*
4
9 9
15 7
10 19
21 20
Your Output
NO
YES
NO

  */
