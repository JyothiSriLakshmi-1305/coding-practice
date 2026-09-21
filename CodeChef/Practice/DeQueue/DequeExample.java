import java.util.ArrayDeque;
import java.util.Deque;

class DequeExample {
    public static void main(String[] args) {
        Deque<Integer> myDeque=new ArrayDeque<>();

        myDeque.addFirst(3);
        myDeque.addLast(2);
        myDeque.addFirst(1);

        System.out.print("Deque: ");

        for(int element:myDeque) {
            System.out.print(element+" ");
        }

        System.out.println();

        int frontElement=myDeque.removeFirst();
        int rearElement=myDeque.removeLast();

        System.out.println("Removed from front: "+frontElement);
        System.out.println("Removed from rear: "+rearElement);

        System.out.print("Updated Deque: ");

        for(int element:myDeque) {
            System.out.print(element+" ");
        }

        System.out.println();
    }
}
/*
Output:
Deque: 1 3 2 
Removed from front: 1
Removed from rear: 2
Updated Deque: 3 

  */
