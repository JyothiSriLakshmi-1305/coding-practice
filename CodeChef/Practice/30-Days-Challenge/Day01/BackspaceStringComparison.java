/*Given two strings bob and alice, where # represents a backspace, process both strings and compare their final forms.

If both processed strings are equal, print YES; otherwise, print NO.
  */

import java.util.Scanner;

class BackspaceStringComparison {
    static String process(String s) {
        StringBuilder result=new StringBuilder();

        for(char c:s.toCharArray()) {
            if(c=='#') {
                if(result.length()>0) {
                    result.deleteCharAt(result.length()-1);
                }
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    static boolean userLogic(String bob,String alice) {
        return process(bob).equals(process(alice));
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        String bob=sc.nextLine();
        String alice=sc.nextLine();

        System.out.println(userLogic(bob,alice)?"YES":"NO");

        sc.close();
    }
}

/*
Sample Testcase 1
Testcase Input
a#c
b
Testcase Output
NO
  */
