class QueueOddEven {
    private static final int maxSize=101;
    private static int[] a=new int[maxSize];
    private static int front=0;
    private static int rear=-1;
    private static int currentSize;

    private static boolean isEmpty() {
        return currentSize==0;
    }

    private static boolean isFull() {
        return currentSize==maxSize;
    }

    private static int size() {
        return currentSize;
    }

    private static void enqueue(int item) {
        if(isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }

        rear=(rear+1)%maxSize;
        a[rear]=item;
        currentSize++;
    }

    private static int dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1;
        }

        int removedItem=a[front];
        front=(front+1)%maxSize;
        currentSize--;

        return removedItem;
    }

    public static void main(String[] args) {
        int n=10;

        for(int i=1;i<=n;i++) {
            if(i%2==1) {
                System.out.print(i+" ");
            } else {
                enqueue(i);
            }
        }

        while(!isEmpty()) {
            System.out.print(dequeue()+" ");
        }
    }
}

/*
1 3 5 7 9 2 4 6 8 10
  */
