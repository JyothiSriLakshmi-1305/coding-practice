import java.util.Scanner;

class ChefAndChocolates {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int c=sc.nextInt();
            int x=sc.nextInt();
            int y=sc.nextInt();

            int money=(c-x)*y;

            System.out.println(money);
        }

        sc.close();
    }
}
