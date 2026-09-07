import java.util.Scanner;

class DivisiblePairs {
    public static long countDivisiblePairs(int[] arr) {
        long evenCount=0;
        long oddCount=0;

        for(int num:arr) {
            if(num%2==0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        long evenPairs=(evenCount*(evenCount-1))/2;
        long oddPairs=(oddCount*(oddCount-1))/2;

        return evenPairs+oddPairs;
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            int[] arr=new int[n];

            for(int i=0;i<n;i++) {
                arr[i]=sc.nextInt();
            }

            long result=countDivisiblePairs(arr);
            System.out.println(result);
        }

        sc.close();
    }
}

/*Input:
2
4
1 2 3 4
5
1 2 3 4 5
Output
2
4

  */
