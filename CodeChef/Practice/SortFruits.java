import java.util.ArrayList;
import java.util.Collections;

class SortFruits {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();

        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Orange");
        fruits.add("Grapes");
        fruits.add("Mango");

        System.out.println("Before sorting: " + fruits);

        Collections.sort(fruits);

        System.out.println("After sorting: " + fruits);
    }
}

/*
Your Output
Before sorting: [Banana, Apple, Orange, Grapes, Mango]
After sorting: [Apple, Banana, Grapes, Mango, Orange]
*/
