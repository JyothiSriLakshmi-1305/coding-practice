import java.util.Scanner;

class DivisionByZero {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c;

        try {
            c = a / b;
            System.out.println(c);
        }
        catch (ArithmeticException e) {
            System.out.println("Division not allowed.");
        }

        sc.close();
    }
}

/* 
Sample Input
10
2
Your Output
5  

Sample 2:
Input
Output
15
0
Division not allowed.*/ 
