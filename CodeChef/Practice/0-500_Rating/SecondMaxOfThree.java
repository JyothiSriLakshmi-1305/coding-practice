import java.util.Arrays;
import java.util.Scanner;

class SecondMaxOfThree {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();

        while(t-->0) {
            int[] nums=new int[3];

            nums[0]=scanner.nextInt();
            nums[1]=scanner.nextInt();
            nums[2]=scanner.nextInt();

            Arrays.sort(nums);

            System.out.println(nums[1]);
        }

        scanner.close();
    }
}


/*
Sample Input
3
1 2 3
10 15 5
100 999 500
Your Output
2
10
500
  */
