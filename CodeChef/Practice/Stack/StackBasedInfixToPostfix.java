import java.util.Scanner;
import java.util.Stack;

class StackBasedInfixToPostfix {
    static int getPrecedence(char op) {
        switch(op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }

    static String infixToPostfix(String infix) {
        Stack<Character> operatorStack=new Stack<>();
        StringBuilder postfix=new StringBuilder();

        for(char c:infix.toCharArray()) {
            if(Character.isLetterOrDigit(c)) {
                postfix.append(c);
            } else if(c=='(') {
                operatorStack.push(c);
            } else if(c==')') {
                while(!operatorStack.isEmpty()&&operatorStack.peek()!='(') {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.pop();
            } else {
                while(!operatorStack.isEmpty()&&operatorStack.peek()!='('&&
                    (getPrecedence(c)<getPrecedence(operatorStack.peek())||
                    (getPrecedence(c)==getPrecedence(operatorStack.peek())&&c!='^'))) {
                    postfix.append(operatorStack.pop());
                }
                operatorStack.push(c);
            }
        }

        while(!operatorStack.isEmpty()) {
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            String infix=sc.next();
            System.out.println(infixToPostfix(infix));
        }

        sc.close();
    }
}
