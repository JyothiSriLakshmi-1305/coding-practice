```java
/*
Given an array of N flower types arranged in non-decreasing order,
find two different indexes whose elements add up to the target sum t.

If multiple pairs exist, select the first occurrence.
Print the two zero-based indexes in increasing order.

Approach: Two Pointer Technique
*/

import java.util.Scanner;

class TwoFlowersSum {

    static void findFlowerIndices(int n, int t, int[] arr, int[] result) {
        int left=0;
        int right=n-1;

        while(left<right) {
            int sum=arr[left]+arr[right];

            if(sum==t) {
                result[0]=left;
                result[1]=right;
                return;
            }
            else if(sum<t) {
                left++;
            }
            else {
                right--;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int t=sc.nextInt();

        int[] arr=new int[n];

        for(int i=0;i<n;i++) {
            arr[i]=sc.nextInt();
        }

        int[] result=new int[2];

        findFlowerIndices(n,t,arr,result);

        System.out.println(result[0]+" "+result[1]);

        sc.close();
    }
}

/*
Sample Testcase 0

Testcase Input
7 5
1 2 2 4 5 7 10

Testcase Output
0 3


Sample Testcase 1

Testcase Input
5 2
1 1 2 3 4

Testcase Output
0 1


Time Complexity: O(n)
Auxiliary Space Complexity: O(1)
*/```
