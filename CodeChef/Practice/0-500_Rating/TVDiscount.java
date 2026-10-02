
import java.util.Scanner;

class TVDiscount {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int t=scanner.nextInt();

        while(t-->0) {
            int a=scanner.nextInt();
            int b=scanner.nextInt();
            int c=scanner.nextInt();
            int d=scanner.nextInt();

            int first=a-c;
            int second=b-d;

            if(first<second) {
                System.out.println("first");
            } else if(first>second) {
                System.out.println("second");
            } else {
                System.out.println("any");
            }
        }

        scanner.close();
    }
}
