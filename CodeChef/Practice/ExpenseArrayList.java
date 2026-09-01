import java.util.ArrayList;

class ExpenseArrayList {
    public static void main(String[] args) {

        ArrayList<Double> expenses = new ArrayList<>();

        expenses.add(2000.50);
        expenses.add(1500.75);
        expenses.add(800.25);
        expenses.add(1200.00);
        expenses.add(500.00);

        System.out.println("Rent Expense: " + expenses.get(0));
        System.out.println("Grocery Expense: " + expenses.get(1));

        ArrayList<Double> newExpenses = new ArrayList<>();

        newExpenses.add(100.00);
        newExpenses.add(150.00);
        newExpenses.add(75.00);

        System.out.println("Internet Bill: " + newExpenses.get(0));
        System.out.println("Gym Membership: " + newExpenses.get(1));
    }
}

/*

Rent Expense: 2000.5
Grocery Expense: 1500.75
Internet Bill: 100.0
Gym Membership: 150.0
*/
