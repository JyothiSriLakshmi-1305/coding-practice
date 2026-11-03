import java.util.Scanner;
import java.util.Stack;

class EvaluateExpression {
    static int evaluateRPN(String s) {
        Stack<Integer> stack=new Stack<>();

        for(char c:s.toCharArray()) {
            if(Character.isDigit(c)) {
                stack.push(c-'0');
            } else {
                int b=stack.pop();
                int a=stack.pop();

                switch(c) {
                    case '+':
                        stack.push(a+b);
                        break;
                    case '-':
                        stack.push(a-b);
                        break;
                    case '*':
                        stack.push(a*b);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            int n=sc.nextInt();
            String s=sc.next();

            System.out.println(evaluateRPN(s));
        }

        sc.close();
    }
}
