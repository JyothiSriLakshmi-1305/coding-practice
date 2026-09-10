
import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String S = br.readLine();
            
            System.out.println(evaluateRPN(S));
        }
    }
    
    private static int evaluateRPN(String S) {
        Stack<Integer> stack = new Stack<>();
        
        for (char c : S.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else {
                int b = stack.pop();
                int a = stack.pop();
                
                switch (c) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                }
            }
        }
        
        return stack.pop();
    }
}
/*
Input
Output
4
3
73-
7
04*3*0-
3
27*
7
703*-9-
4
0
14
-2*/
