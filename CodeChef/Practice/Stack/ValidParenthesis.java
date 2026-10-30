import java.util.Scanner;

class ValidParenthesis {
    static char[] a;
    static int top;

    static void push(char ele) {
        if(top<a.length-1) {
            a[++top]=ele;
        }
    }

    static char pop() {
        if(top>=0) {
            return a[top--];
        }
        return '-';
    }

    static boolean isEmpty() {
        return top==-1;
    }

    static boolean isFull() {
        return top==a.length-1;
    }

    static boolean isMatchingPair(char open,char close) {
        return open=='('&&close==')';
    }

    static boolean isBalanced(String expression) {
        a=new char[expression.length()];
        top=-1;

        for(int i=0;i<expression.length();i++) {
            char c=expression.charAt(i);

            if(c=='(') {
                push(c);
            } else if(c==')') {
                if(isEmpty()) {
                    return false;
                }

                char topChar=pop();

                if(!isMatchingPair(topChar,c)) {
                    return false;
                }
            }
        }

        return isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int t=sc.nextInt();

        while(t-->0) {
            String str=sc.next();
            System.out.println(str+" : "+isBalanced(str));
        }

        sc.close();
    }
}
