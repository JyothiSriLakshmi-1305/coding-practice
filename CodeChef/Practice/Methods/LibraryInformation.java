import java.util.Scanner;

class LibraryInformation {

    public static void displayLibraryInfo() {
        Scanner scanner = new Scanner(System.in);

        String libraryName = scanner.nextLine();
        int numberOfBooks = scanner.nextInt();
        scanner.nextLine();
        String libraryAddress = scanner.nextLine();

        System.out.println("Library Information:");
        System.out.println("Library Name: " + libraryName);
        System.out.println("Number of Books: " + numberOfBooks);
        System.out.println("Library Address: " + libraryAddress);
    }

    public static void main(String[] args) {
        displayLibraryInfo();
    }
}

/*
Sample 1:
Input
Output
Central Library
5000
Main Street
Library Information:
Library Name: Central Library
Number of Books: 5000
Library Address: Main Street
  */
