import java.util.Scanner;

class BoneAppetit {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int m=sc.nextInt();
        int x=sc.nextInt();
        int y=sc.nextInt();

        System.out.println((n*x)+(m*y));

        sc.close();
    }
}
/*


Sample Input
4 2
5 6
Your Output
32
*/
