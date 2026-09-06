class StackOperations {
    static int peek() {
        if(top>=0) {
            int ele=a[top];
            System.out.println("Peeked: "+ele);
            return ele;
        } else {
            System.out.println("Stack is empty. Cannot peek.");
            return -1;
        }
    }

    static boolean is_empty() {
        return top==-1;
    }

    static boolean is_full() {
        return top>=MAX_SIZE-1;
    }
}

/* Output:

Your Output
Pushed: 1
Pushed: 2
Pushed: 3
Peeked: 3
Popped: 3
Popped: 2
Popped: 1
Stack is empty. Cannot pop.
Stack is empty. Cannot peek.

  */
