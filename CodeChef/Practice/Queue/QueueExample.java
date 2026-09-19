import java.util.LinkedList;
import java.util.Queue;

class QueueExample {
    public static void main(String[] args) {
        Queue<Integer> myQueue=new LinkedList<>();

        myQueue.offer(10);
        myQueue.offer(20);
        myQueue.offer(30);

        System.out.println("Front element: "+myQueue.peek());

        System.out.print("Queue elements: ");

        while(!myQueue.isEmpty()) {
            System.out.print(myQueue.poll()+" ");
        }

        System.out.println();

        if(myQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Queue is not empty.");
        }
    }
}
/*
Front element: 10
Queue elements: 10 20 30 
Queue is empty.*/
