class CircularQueue {
    private static final int maxSize=101;
    private int[] a=new int[maxSize];
    private int front=0;
    private int rear=-1;
    private int currentSize;

    public boolean isEmpty() {
        return currentSize==0;
    }

    public boolean isFull() {
        return currentSize==maxSize;
    }

    public int size() {
        return currentSize;
    }

    public void enqueue(int item) {
        if(isFull()) {
            System.out.println("Queue is full. Cannot enqueue.");
            return;
        }

        rear=(rear+1)%maxSize;
        a[rear]=item;
        currentSize++;
    }

    public int dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return -1;
        }

        int removedItem=a[front];
        front=(front+1)%maxSize;
        currentSize--;

        return removedItem;
    }
}

/*
Enqueued: 10
Enqueued: 20
Enqueued: 30
Enqueued: 40
Enqueued: 50
Is the queue empty? false
Dequeued: 10
Dequeued: 20
Dequeued: 30
Dequeued: 40
Dequeued: 50
Is the queue empty? true*/
