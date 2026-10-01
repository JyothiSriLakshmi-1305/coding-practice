/*
On every CodeChef user's profile page, the list of problems that they have set, tested, and written editorials for, is listed at the bottom.

Given the number of problems in each of these 
3
3 categories as 
X
,
Y
,
X,Y, and 
Z
Z respectively (where all three integers are distinct), find if the user has been most active as a Setter, Tester, or Editorialist.

*/
import java.util.Scanner;

class Speciality {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);

        int t=scanner.nextInt();

        while(t-->0) {
            int x=scanner.nextInt();
            int y=scanner.nextInt();
            int z=scanner.nextInt();

            if(x>Math.max(y,z)) {
                System.out.println("Setter");
            } else if(y>Math.max(x,z)) {
                System.out.println("Tester");
            } else {
                System.out.println("Editorialist");
            }
        }

        scanner.close();
    }
}

/*
Sample Input
4
5 3 2
1 2 4
2 5 1
9 4 5
Your Output
Setter
Editorialist
Tester
Setter

  */
