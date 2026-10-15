
import java.util.Scanner;

class BobAtTheBank {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int w=sc.nextInt();
            int x=sc.nextInt();
            int y=sc.nextInt();
            int z=sc.nextInt();

            int balance=w+(x-y)*z;

            System.out.println(balance);
        }

        sc.close();
    }
}
