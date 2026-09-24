import java.util.Scanner;

class AudibleRange {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();

            if(x>=67&&x<=45000) {
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
5
42
67
402
45000
45005
Your Output
No
Yes
Yes
Yes
No
*/
