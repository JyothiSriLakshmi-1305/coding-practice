import java.util.Scanner;

class IntegerValidation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            int num = Integer.parseInt(scanner.nextLine());
            System.out.println(num);
        } 
        catch (NumberFormatException e) {
            System.out.println("Invalid Integer");
        }

        scanner.close();
    }
}

/* 
ample 1:
Input
Output
42
42
Sample 2:
Input
Output
JK
Invalid Integer
  */
