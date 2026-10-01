```java
/*
Given a string of n lowercase Latin letters, reverse the string
and find the kth character from the reversed string.

Instead of reversing the string, the kth character in the reversed
string can be directly found using the index n-k in the original string.
*/

import java.util.Scanner;

class KthCharacterAfterReverse {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();
        int k=sc.nextInt();
        String s=sc.next();

        System.out.println(s.charAt(n-k));

        sc.close();
    }
}

/*
Sample Testcase 0

Testcase Input
5 2
abdfa

Testcase Output
f


Sample Testcase 1

Testcase Input
4 4
bbxn

Testcase Output
b
*/
```
