import java.util.Scanner;

class IncorrectLogicalOperators {
    public static void main(String[] args) {
        // Initialize variables
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();

        // Check conditions and print grades
        // Potential Logical Error: Ensure the correct comparison operators are used
        if (score >= 90 && score <= 100) {
            System.out.println("Grade: A");
        }
        else if (score >= 80 && score < 90) {
            System.out.println("Grade: B");
        }
        else if (score >= 70 && score < 80) {
            System.out.println("Grade: C");
        }
        else {
            System.out.println("Grade: D or F");
        }

        // Printing the score
        System.out.println("The score is: " + score);

        scanner.close();
    }
}

//output
/*Sample Input
75
Your Output
Grade: C
The score is: 75
*/
