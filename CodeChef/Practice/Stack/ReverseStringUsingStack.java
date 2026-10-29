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
        }

        char character=stackArray[topIndex];
        topIndex--;
        return character;
    }

    boolean isEmpty() {
        return topIndex==-1;
    }

    boolean isFull() {
        return topIndex>=STACK_CAPACITY-1;
    }
}

class ReverseStringUsingStack {
    public static void main(String[] args) {
        String inputString="Hello, World!";
        Stack charStack=new Stack();

        for(int i=0;i<inputString.length();i++) {
            charStack.push(inputString.charAt(i));
        }

        StringBuilder reversedString=new StringBuilder();

        while(!charStack.isEmpty()) {
            reversedString.append(charStack.pop());
        }

        System.out.println(reversedString);
    }
}
