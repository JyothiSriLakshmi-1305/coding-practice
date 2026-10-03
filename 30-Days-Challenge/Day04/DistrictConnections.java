
```java
/*
Given N districts, each having a unique code and an initial rating,
process three types of operations:

1. LINK X Y - Connect the groups containing districts X and Y.
2. BOOST X V - Increase the rating of district X by V.
3. QUERY X - Find the maximum rating in X's connected group.

Use Disjoint Set Union (DSU) with Path Compression
and Union by Size to efficiently manage connected groups.
*/

import java.util.*;

class DistrictConnections {

    static int[] parent;
    static int[] size;
    static long[] rating;
    static long[] maxRating;

    static int find(int x) {
        if(parent[x]!=x) {
            parent[x]=find(parent[x]);
        }
        return parent[x];
    }

    static void union(int a,int b) {
        int rootA=find(a);
        int rootB=find(b);

        if(rootA==rootB) {
            return;
        }

        if(size[rootA]<size[rootB]) {
            int temp=rootA;
            rootA=rootB;
            rootB=temp;
        }

        parent[rootB]=rootA;
        size[rootA]+=size[rootB];

        maxRating[rootA]=Math.max(maxRating[rootA],maxRating[rootB]);
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        int n=sc.nextInt();

        Map<String,Integer> map=new HashMap<>();

        parent=new int[n];
        size=new int[n];
        rating=new long[n];
        maxRating=new long[n];

        for(int i=0;i<n;i++) {
            String code=sc.next();
            long value=sc.nextLong();

            map.put(code,i);

            parent[i]=i;
            size[i]=1;
            rating[i]=value;
            maxRating[i]=value;
        }

        int q=sc.nextInt();

        while(q-->0) {
            String operation=sc.next();
            String code=sc.next();

            int x=map.get(code);

            if(operation.equals("LINK")) {
                String secondCode=sc.next();
                int y=map.get(secondCode);

                union(x,y);
            }
            else if(operation.equals("BOOST")) {
                long value=sc.nextLong();

                rating[x]+=value;

                int root=find(x);

                maxRating[root]=Math.max(maxRating[root],rating[x]);
            }
            else if(operation.equals("QUERY")) {
                int root=find(x);

                System.out.println(maxRating[root]);
            }
        }

        sc.close();
    }
}

/*
Sample Testcase 0

Testcase Input
4
D1 50
D2 30
D3 70
D4 10
6
QUERY D3
LINK D1 D2
BOOST D2 40
QUERY D1
LINK D3 D4
QUERY D4

Testcase Output
70
70
70


Sample Testcase 1

Testcase Input
3
D1 5
D2 100
D3 20
5
QUERY D2
BOOST D1 200
QUERY D1
LINK D1 D3
QUERY D3

Testcase Output
100
205
205


Approach: Disjoint Set Union (DSU)
Path Compression + Union by Size

Time Complexity: O((N+Q) * α(N)) amortized
Space Complexity: O(N)
*/
```
