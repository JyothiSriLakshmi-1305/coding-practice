import java.util.Scanner;

class CircularQueueExample {
    static final int maxSize=101;
    static int[] a=new int[maxSize];
    static int front=0;
    static int rear=-1;
    static int currentSize=0;

    static boolean isEmpty() {
        return currentSize==0;
    }

    static boolean isFull() {
        return currentSize==maxSize;
    }

    static int size() {
        return currentSize;
    }

    static void enqueue(int item) {
        if(isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }

        rear=(rear+1)%maxSize;
        a[rear]=item;
        currentSize++;
    }

    static int dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1;
        }

        int removedItem=a[front];
        front=(front+1)%maxSize;
        currentSize--;

        return removedItem;
    }

    static boolean contains(int task) {
        int idx=front;

        for(int i=0;i<currentSize;i++) {
            if(a[idx]==task) {
                return true;
            }

            idx=(idx+1)%maxSize;
        }

        return false;
    }

    static void addTask(int task) {
        if(!contains(task)) {
            enqueue(task);
        }
    }

    static void displayToDoList() {
        int count=currentSize;

        for(int i=0;i<count;i++) {
            int task=dequeue();
            System.out.println(task);
        }
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int n=10;

        for(int i=0;i<n;i++) {
            int task=scanner.nextInt();

            if(task==-1) {
                break;
            }

            addTask(task);
        }

        displayToDoList();
        scanner.close();
    }
}
/*
Input

1
2
4
5
1
2
3
9
4
9
output:
1
2
4
5
3
9
  */
