import java.util.Scanner;

class SalesMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] sales = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sales[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < 3; i++) {
            int rowSum = 0;

            for (int j = 0; j < 3; j++) {
                rowSum += sales[i][j];
            }

            System.out.println("Row " + i + " total: " + rowSum);
        }

        for (int j = 0; j < 3; j++) {
            int colSum = 0;

            for (int i = 0; i < 3; i++) {
                colSum += sales[i][j];
            }

            System.out.println("Column " + j + " total: " + colSum);
        }

        scanner.close();
    }
}

/*
Sample 1:
Input
Output
10 20 30
15 25 35
5 10 15
Row 0 total: 60  
Row 1 total: 75  
Row 2 total: 30  
Column 0 total: 30  
Column 1 total: 55  
Column 2 total: 80  
*/
