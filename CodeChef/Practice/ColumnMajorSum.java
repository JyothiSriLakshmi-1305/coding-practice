class ColumnMajorSum {
    public static void main(String[] args) {

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        int sum = 0;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                sum += matrix[i][j];
            }
        }

        System.out.println("Total Sum: " + sum);
    }
}

/*
Total Sum: 45
*/
