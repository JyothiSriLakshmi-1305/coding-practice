
import java.util.Scanner;

class TyreProblem {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int t=scanner.nextInt();

        while(t-->0) {
            int n=scanner.nextInt();
            int m=scanner.nextInt();

            int total=(n*2)+(m*4);

            System.out.println(total);
        }

        scanner.close();
    }
}

/*
Sample Input
2
2 1
3 0
Your Output
8
6
*/
