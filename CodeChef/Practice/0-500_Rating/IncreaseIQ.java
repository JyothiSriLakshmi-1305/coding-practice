
import java.util.Scanner;

class IncreaseIQ {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int x=sc.nextInt();

        if(x+7>170) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        sc.close();
    }
}
/*Sample Input
165
Your Output
Yes
*/
