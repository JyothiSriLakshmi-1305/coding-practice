import java.util.ArrayList;

class EmployeeNames {
    public static void main(String[] args) {

        ArrayList<String> employeeNames = new ArrayList<>();

        employeeNames.add("Alex");
        employeeNames.add("Bobby");

        System.out.println("Employee 1: " + employeeNames.get(0));
        System.out.println("Employee 2: " + employeeNames.get(1));
    }
}

/*
Employee 1: Alex
Employee 2: Bobby
  */
