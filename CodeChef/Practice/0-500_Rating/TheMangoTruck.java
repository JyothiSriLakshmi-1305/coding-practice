
import java.util.Scanner;

class TheMangoTruck {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();
            int z=sc.nextInt();

            int mangoes=(z-y)/x;

            System.out.println(mangoes);
        }

        sc.close();
    }
}
