import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class NecklaceRotation {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int t=scanner.nextInt();

        while(t-->0) {
            int n=scanner.nextInt();
            int k=scanner.nextInt();

            Queue<Integer> necklace=new LinkedList<>();

            for(int i=0;i<n;i++) {
                int pearl=scanner.nextInt();
                necklace.add(pearl);
            }

            for(int i=0;i<k;i++) {
                int removed=necklace.poll();
                necklace.add(removed);
            }

            while(!necklace.isEmpty()) {
                int pearl=necklace.poll();
                System.out.print(pearl+" ");
            }

            System.out.println();
        }

        scanner.close();
    }
}

/*
2
5 3
1 5 3 4 2
6 5
10 1 2 9 8 2
Your Output
4 2 1 5 3 
2 10 1 2 9 8 
  */
