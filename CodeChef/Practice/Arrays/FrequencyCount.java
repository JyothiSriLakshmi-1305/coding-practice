import java.util.Scanner;

class FrequencyCount {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();

        while(t-->0) {
            int n=scanner.nextInt();
            int[] arr=new int[n];

            for(int i=0;i<n;i++) {
                arr[i]=scanner.nextInt();
            }

            for(int i=0;i<n;i++) {
                int count=0;

                for(int j=0;j<n;j++) {
                    if(arr[i]==arr[j]) {
                        count++;
                    }
                }

                System.out.print(count+" ");
            }

            System.out.println();
        }

        scanner.close();
    }
}
/*
Input

3
10
1 2 1 2 1 3 4 1 2 3
5 
1 1 1 1 1
5
1 2 1 2 1
  Output::
4 3 4 3 4 2 1 4 3 2
5 5 5 5 5
3 2 3 2 3
  */
