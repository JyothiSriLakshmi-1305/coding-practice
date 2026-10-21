
import java.util.Scanner;

class Playlist {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            int x=sc.nextInt();

            int count=n/(3*x);

            System.out.println(count);
        }

        sc.close();
    }
}
