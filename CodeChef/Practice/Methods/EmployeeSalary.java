import java.util.Scanner;

class EmployeeSalary {

    public static double calculateSalary(double basicSalary) {
        double HRA = 0.10 * basicSalary;
        double DA = 0.05 * basicSalary;

        return basicSalary + HRA + DA;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double basicSalary = scanner.nextDouble();

        double grossSalary = calculateSalary(basicSalary);

        double HRA = 0.10 * basicSalary;
        double DA = 0.05 * basicSalary;

        System.out.println("Employee Salary Details:");
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("HRA: " + HRA);
        System.out.println("DA: " + DA);
        System.out.println("Gross Salary: " + grossSalary);

        scanner.close();
    }
}
/*
Sample 1:
Input
Output
30000
Employee Salary Details:
Basic Salary: 30000.0
HRA: 3000.0
DA: 1500.0
Gross Salary: 34500.0
*/
