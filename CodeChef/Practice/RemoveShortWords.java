import java.util.ArrayList;
import java.util.Scanner;

class RemoveShortWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            words.add(sc.nextLine());
        }

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).length() < 4) {
                words.remove(i);
                i--;
            }
        }

        System.out.println(words);

        sc.close();
    }
}

/*
Sample 1:
Input
Output
6
Sun
Rain
Tree
Sky
Cloud
Moon
[Rain, Tree, Cloud, Moon]
  */
