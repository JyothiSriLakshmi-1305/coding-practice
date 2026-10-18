
import java.util.Scanner;

class BucketAndWaterFlow {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int w=sc.nextInt();
            int x=sc.nextInt();
            int y=sc.nextInt();
            int z=sc.nextInt();

            int total=w+y*z;

            if(total>x) {
                System.out.println("overflow");
            } else if(total==x) {
                System.out.println("filled");
            } else {
                System.out.println("unfilled");
            }
        }

        sc.close();
    }
}
