class Stack {
    private static final int STACK_CAPACITY=101;
    private char[] stackArray=new char[STACK_CAPACITY];
    private int topIndex=-1;

    void push(char character) {
        if(isFull()) {
            System.out.println("Stack is full");
        } else {
            topIndex++;
            stackArray[topIndex]=character;
        }
    }

    char pop() {
        if(isEmpty()) {
            return '-';
        } else {
            char character=stackArray[topIndex];
            topIndex--;
            return character;
        }
    }

    boolean isEmpty() {
        return topIndex==-1;
    }

    boolean isFull() {
        return topIndex>=STACK_CAPACITY-1;
    }
}

class ReverseString {
    public static void main(String[] args) {
        String inputString="Hello, World!";
        int inputLength=inputString.length();

        Stack charStack=new Stack();

        for(int i=0;i<inputLength;i++) {
            char currentChar=inputString.charAt(i);
            charStack.push(currentChar);
        }

        StringBuilder reversedString=new StringBuilder();

        while(!charStack.isEmpty()) {
            reversedString.append(charStack.pop());
        }

        System.out.println(reversedString.toString());
    }
}

/* Output:

Sample Input
Hello, World!
Your Output
!dlroW ,olleH

  */
