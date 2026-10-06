import java.util.Scanner;

class CreditScore {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int x=scanner.nextInt();

        if(x>=750) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        scanner.close();
    }
}

/*
Sample Input
823
Your Output
YES
  */
