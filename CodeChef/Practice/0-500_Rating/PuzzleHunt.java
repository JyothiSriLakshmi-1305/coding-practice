import java.util.Scanner;

class PuzzleHunt {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();

        if(n>=6&&n<=8) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        sc.close();
    }
}

/*
Sample Input
4
Your Output
No*/
