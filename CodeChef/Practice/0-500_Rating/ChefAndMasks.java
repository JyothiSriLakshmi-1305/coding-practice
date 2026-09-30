import java.util.Scanner;

class ChefAndMasks {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int disposablePrice=sc.nextInt();
            int clothPrice=sc.nextInt();

            if(100*disposablePrice<10*clothPrice) {
                System.out.println("Disposable");
            } else {
                System.out.println("Cloth");
            }
        }

        sc.close();
    }
}
