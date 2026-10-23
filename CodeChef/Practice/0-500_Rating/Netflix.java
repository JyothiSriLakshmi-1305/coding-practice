import java.util.Scanner;

class Netflix {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int a=sc.nextInt();
            int b=sc.nextInt();
            int c=sc.nextInt();
            int x=sc.nextInt();

            if(a+b>=x||a+c>=x||b+c>=x) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

        sc.close();
    }
}
