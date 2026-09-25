import java.util.Scanner;

class BullOrBear {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();

        while(t-->0) {
            int x=sc.nextInt();
            int y=sc.nextInt();

            if(x<y) {
                System.out.println("Profit");
            } else if(x>y) {
                System.out.println("Loss");
            } else {
                System.out.println("Neutral");
            }
        }

        sc.close();
    }
}

/*
Sample Input
4
4 2
8 8
3 4
2 1
Your Output
Loss
Neutral
Profit
Loss
  */
