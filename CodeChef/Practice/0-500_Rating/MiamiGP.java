
import java.util.Scanner;

class MiamiGP {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();

            if(100*y<=107*x) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}
