import java.util.ArrayList;
import java.util.Scanner;

class FavoriteFruits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        ArrayList<String> fruits = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            fruits.add(sc.nextLine());
        }

        int m = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < m; i++) {
            String favoriteFruit = sc.nextLine();
            System.out.println(fruits.contains(favoriteFruit));
        }

        sc.close();
    }
}

/*Output:
Sample 1:
Input
Output
5
Apple
Banana
Orange
Mango
Grapes
3
Banana
Pineapple
Mango
true
false
true
  */
