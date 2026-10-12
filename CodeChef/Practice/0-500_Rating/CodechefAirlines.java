
import java.util.Scanner;

class CodechefAirlines {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();
            int z=sc.nextInt();

            int seats=10*x;
            int passengers=Math.min(seats,y);
            int earnings=passengers*z;

            System.out.println(earnings);
        }

        sc.close();
    }
}
