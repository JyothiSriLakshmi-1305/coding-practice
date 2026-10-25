import java.util.Scanner;

class BestCoupon {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();

            System.out.println(Math.max(x/10,100));
        }

        sc.close();
    }
}
