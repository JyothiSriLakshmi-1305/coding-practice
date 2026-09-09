import java.util.Scanner;
import java.util.Stack;

class InfixToPostfix {
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
            int precedence=getPrecedence(c);

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
                while(!operatorStack.isEmpty()&&precedence<=getPrecedence(operatorStack.peek())) {
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
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();
        scanner.nextLine();

        while(t-->0) {
            String infixExpression=scanner.nextLine();
            String postfixExpression=infixToPostfix(infixExpression);
            System.out.println(postfixExpression);
        }

        scanner.close();
    }
}

/*
3
(a+(b*c))
((a+b)*(z+x))
((a+t)*((b+(a+c))^(c+d)))
Your Output
abc*+
ab+zx+*
at+bac++cd+^*
*/
