import java.util.Scanner;

class ChefAndGym {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();
            int z=sc.nextInt();

            if(x+y<=z) {
                System.out.println(2);
            } else if(x<=z) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }

        sc.close();
    }
}
