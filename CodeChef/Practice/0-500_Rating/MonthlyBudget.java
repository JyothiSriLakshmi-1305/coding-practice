import java.util.Scanner;

class MonthlyBudget {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int t=scanner.nextInt();

        while(t-->0) {
            int x=scanner.nextInt();
            int y=scanner.nextInt();

            int expense=y*30;

            if(x>=expense) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        scanner.close();
    }
}

/*

Sample Input
3
1000 10
250 50
1500 50
Your Output
YES
NO
YES
  */
