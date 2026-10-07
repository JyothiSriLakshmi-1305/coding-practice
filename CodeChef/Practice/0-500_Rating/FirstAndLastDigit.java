import java.util.Scanner;

class FirstAndLastDigit {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();

            int last=n%10;
            int first=n;

            while(first>=10) {
                first/=10;
            }

            int sum=first+last;

            System.out.println(sum);
        }

        sc.close();
    }
}
/*
Sample Input
3 
1234
124894
242323
Your Output
5
5
5
  */
