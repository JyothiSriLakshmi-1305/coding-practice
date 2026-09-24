import java.util.Scanner;

class OffByOne {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int a=sc.nextInt();
        int b=sc.nextInt();

        System.out.println((a+b)+"1");

        sc.close();
    }
}
/*
Sample 1:
Input

3 5
Output
81
Explanation:
3
+
5
=
8
3+5=8, and the calculator appends a 
1
1 to print 
81
81
  */
