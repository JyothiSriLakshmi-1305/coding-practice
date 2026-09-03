import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class FirstLastLanguage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();

        ArrayList<String> languages = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            languages.add(sc.nextLine());
        }

        Collections.sort(languages);

        System.out.println("First language: " + languages.get(0));
        System.out.println("Last Language: " + languages.get(languages.size() - 1));

        sc.close();
    }
}

/*
Sample 1:
Input
Output
5
Java
Python
C++
Ruby
Go
First Language: C++
Last Language: Ruby
  */
