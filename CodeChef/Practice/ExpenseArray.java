class ExpenseArray {
    public static void main(String[] args) {

        double[] expenses = {2000.50, 1500.75, 800.25, 1200.00, 500.00};

        System.out.println("Rent Expense: " + expenses[0]);
        System.out.println("Grocery Expense: " + expenses[1]);

        double[] newExpenses = new double[3];

        newExpenses[0] = 100.00;
        newExpenses[1] = 150.00;
        newExpenses[2] = 75.50;

        System.out.println("Internet Bill: " + newExpenses[0]);
        System.out.println("Gym Membership: " + newExpenses[1]);
    }
}

/*

expenses = 2000.50, 1500.75, 800.25, 1200.00, 500.00
newExpenses = 100.00, 150.00, 75.50
Expected Output:

Rent Expense: 2000.5
Grocery Expense: 1500.75
Internet Bill: 100.0
Gym Membership: 150.0

  */
